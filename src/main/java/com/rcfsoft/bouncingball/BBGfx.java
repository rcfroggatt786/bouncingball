package com.rcfsoft.bouncingball;

public class BBGfx {
    public static final int bufferSize = BB.X_SIZE * BB.Y_SIZE * BB.BPP;
    public static final byte[] byteBuffer = new byte[bufferSize];
//    private byte[] backgroundBuffer = initBackground();

//    private byte[] initBackground() {
//        byte[] backBuffer = new byte[bufferSize];
//        for(int y = 0; y < BB.Y_SIZE; y++) {
//            for(int x = 0; x < BB.X_SIZE; x++) {
//                int offset = (x + y * BB.X_SIZE) * BB.BPP;
//                backBuffer[offset++] = (byte) 0;
//                backBuffer[offset++] = (byte) 0;
//                backBuffer[offset++] = (byte) 0;
//                backBuffer[offset  ] = (byte) 255;
//            }
//        }
//        return backBuffer;
//    }
    public static synchronized byte[] getByteBuffer() {
        //System.arraycopy(backgroundBuffer, 0, byteBuffer, 0, bufferSize );
        int offset = 0;
//        for(int y = 0; y < BB.Y_SIZE; y++) {
//            for(int x = 0; x < BB.X_SIZE; x++) {
//                byteBuffer[offset++] = (byte) 0;
//                byteBuffer[offset++] = (byte) 0;
//                byteBuffer[offset++] = (byte) 0;
//                byteBuffer[offset++] = (byte) 255;
//            }
//        }
        offset = 0;
        for(int y = 0; y < BB.Y_SIZE; y++) {
            for(int x = 0; x < BB.X_SIZE; x++) {
                byteBuffer[offset++] = (byte) 65;
                byteBuffer[offset++] = (byte) 65;
                byteBuffer[offset++] = (byte) 65;
                byteBuffer[offset++] = (byte) 65;
            }
        }
        offset = 0;
        for(int y = 0; y < BB.Y_SIZE; y++) {
            for(int x = 0; x < BB.X_SIZE; x++) {
                byteBuffer[offset++] = (byte) 255;
                byteBuffer[offset++] = (byte) 255;
                byteBuffer[offset++] = (byte) 255;
                byteBuffer[offset++] = (byte) 255;
            }
        }
        return byteBuffer;
    }
    private BBGfx() { throw new IllegalStateException("This is a utility class and should not be instantiated"); }
}
