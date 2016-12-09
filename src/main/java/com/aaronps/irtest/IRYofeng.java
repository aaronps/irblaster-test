/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaronps.irtest;

import gnu.io.RXTXPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * To study new codes:
 * <pre>{@code
 *     IRYofeng blaster;
 *     if ( blaster.enterLearnMode() ) {
 *         boolean goodLearn;
 *         goodLearn = blaster.learnCode(code1);
 *         if ( goodLearn ) goodLearn = blaster.learnCode(code2);
 *         if ( goodLearn ) goodLearn = blaster.learnCode(code3);
 *         
 *         blaster.exitLearnMode();
 *     }
 * }</pre>
 * 
 * @author krom
 */
public final class IRYofeng implements SerialPortEventListener
{
    RXTXPort mPort;
    InputStream mInput;
    OutputStream mOutput;
    boolean mLearningState = false;
    final Object readNotifier = new Object();
    volatile boolean hasDataReady = false;
//    final AtomicBoolean hasDataReady = new AtomicBoolean(false);
    
    public IRYofeng(RXTXPort port)
    {
        this.mInput = port.getInputStream();
        this.mOutput = port.getOutputStream();
        
        try
        {
            port.addEventListener(this);
        }
        catch (TooManyListenersException ex)
        {
            Logger.getLogger(IRYofeng.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isLearning() { return mLearningState; }
    
    @SuppressWarnings("empty-statement")
    void flushInput() throws IOException
    {
        while ( mInput.read() != -1 ) ;
        hasDataReady = false;
    }
    
    int sendByteWithResponse(int code) throws IOException, InterruptedException, TimeoutException
    {
        return sendByteWithResponse(code, 5000);
    }
    
    int sendByteWithResponse(int code, int timeout) throws IOException, InterruptedException, TimeoutException
    {
        flushInput();
        
        synchronized(readNotifier)
        {
            mOutput.write(code);
            mOutput.flush();
            readNotifier.wait(timeout);
        }
        
        if ( ! hasDataReady )
        {
            throw new TimeoutException();
        }
        
        final int c = mInput.read();
        if ( c == -1 )
        {
            throw new TimeoutException();
        }
        
        return c;
    }
    
    public boolean sendSignal(int code) throws IOException, InterruptedException, TimeoutException
    {
        final int c = sendByteWithResponse(code);
        
        System.out.println("SendSignal: > " + Integer.toHexString(code&0xff) + " got " + Integer.toHexString(c));
        return c == 0;
    }
    
    public boolean enterLearnMode() throws IOException, InterruptedException, TimeoutException
    {
//        if ( mLearningState )
//            throw new IllegalStateException("It was already in learning mode");
        
        final int c = sendByteWithResponse(0xf0);
        mLearningState = c == 0xf0;
        System.out.println("Enter Learn Mode: > 0xf0 got " + Integer.toHexString(c));
//        if ( c == 0xf0 )
//        {
//            mLearningState = true;
//        }
        return mLearningState;
    }
    
    public boolean learnCode(int code) throws IOException, InterruptedException, TimeoutException
    {
        if ( ! mLearningState )
            throw new IllegalStateException("Not in learning mode");
        
        final int c = sendByteWithResponse(code, 10000);
        
        System.out.println("Learn result " + Integer.toHexString(c));
        
        return c == 0;
    }
    
    public boolean exitLearnMode() throws IOException, InterruptedException, TimeoutException
    {
        final int c = sendByteWithResponse(0xf2);
        mLearningState = false;
        System.out.println("Exit Learn Mode: > 0xf2 got " + Integer.toHexString(c));
        
        return c == 0xf2;
    }
    
    public boolean readBlock(final byte[] buffer) throws IOException
    {
        if ( buffer.length < 256 )
        {
            throw new IllegalArgumentException("Buffer too small, length should be at least 256, it was " + buffer.length);
        }
        
        int checksum = 0;
        
        for (int i = 0; i < buffer.length; i++ )
        {
            final int c = mInput.read();
            if ( c != -1 )
            {
                checksum += c;
                buffer[i] = (byte)c;
            }
            else
            {
                throw new IOException("Error reading bytes");
            }
        }
        
        final int received_checksum = mInput.read();
        return received_checksum == (checksum & 0xff);
    }
    
    public boolean sendBlock(final byte[] data)
    {//27648
        return false;
    }
    
    public String readVersion() throws IOException
    {
        final byte[] buffer = new byte[15];
        for ( int n = 0; n < buffer.length; n++ )
        {
            final int c = mInput.read();
            if ( c != -1 )
                buffer[n] = (byte)c;
            else
                throw new IOException("Error reading version");
        }
        
        return new String(buffer);
    }
    
    /**
     * Reads block from "from" to "to-1"
     * @param from
     * @param count
     * @param buffer
     * @return
     * @throws IOException 
     * @throws InterruptedException 
     */
    public boolean readCustom(int from, int count, byte[] buffer) throws IOException, InterruptedException
    {
        final int bufferLength = count * 256;
        if ( buffer.length < bufferLength )
            throw new IllegalArgumentException("Buffer is too small for data: " + buffer.length + " < " + bufferLength );
    
        System.out.println("Reading from " + from + " to " + (from+count) + " with a wanted buffer length " + bufferLength);
        mOutput.write(0xed);
        mOutput.flush();
        Thread.sleep(10);
        
        mOutput.write(from);
        mOutput.flush();
        Thread.sleep(10);
        
        mOutput.write(from + count);
        mOutput.flush();
        
//        int c = mInput.read();
//        if ( c != 0xdd )
//        {
//            System.out.println("Received something different: " + Integer.toHexString(c));
//            return false;
//        }
        
        int bufferIndex = 0;
        int blockIndex = 0;
        int blockChecksum = 0;
        
        do
        {
            final int c = mInput.read();
            if ( c == -1 )
            {
                System.out.println("Looks like the input closed or something");
                return false;
            }
            
            if ( blockIndex < 256 )
            {
                buffer[bufferIndex++] = (byte)c;
                blockIndex += 1;
                blockChecksum += c;
            }
            else
            {
                if ( c != (blockChecksum&0xff) )
                {
                    System.out.println("Wrong checksum received: " + Integer.toHexString(c) + " != " + Integer.toHexString(blockChecksum&0xff));
                    
                    mOutput.write(0xff);
                    mOutput.flush();
                    
                    return false;
                }
                
                System.out.println("Received good checksum: " + Integer.toHexString(c) + " = " + Integer.toHexString(blockChecksum&0xff));
                
                mOutput.write(0);
                mOutput.flush();
                
                blockIndex = 0;
                blockChecksum = 0;
            }
            
            
        } while ( (bufferIndex < bufferLength) || (blockIndex == 256) );
        
        return true;
    }

    @Override
    public void serialEvent(SerialPortEvent ev)
    {
        if ( ev.getEventType() == SerialPortEvent.DATA_AVAILABLE )
        {
            synchronized (readNotifier)
            {
                hasDataReady = true;
                readNotifier.notify();
            }
        }
    }
    
    
    
}
