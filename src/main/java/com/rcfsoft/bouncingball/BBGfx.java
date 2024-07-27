package com.rcfsoft.bouncingball;

public class BBGfx {
    public static final int bufferSize = BB.X_SIZE * BB.Y_SIZE * BB.BPP;
    public static final byte[] byteBuffer = new byte[bufferSize];
    private static final byte[] backgroundBuffer = initBackground();
    private static final BBBall[] ball = new BBBall[BB.NUM_BALLS];

    private static byte[] initBackground() {
        byte[] backBuffer = new byte[bufferSize];
        for(int y = 0, offset = 0; y < BB.Y_SIZE; y++) {
            for(int x = 0; x < BB.X_SIZE; x++) {
                backBuffer[offset++] = (byte) 0;
                backBuffer[offset++] = (byte) 0;
                backBuffer[offset++] = (byte) 0;
                backBuffer[offset++] = (byte) 255;
            }
        }
        return backBuffer;
    }
    public static void init() {
        for(int i = 0; i < BB.NUM_BALLS; i ++) {
            BBGfx.ball[i] = new BBBall();
        }
    }
    public static byte[] getByteBuffer() {
        System.arraycopy(backgroundBuffer, 0, byteBuffer, 0, bufferSize );
        for(int i = 0; i < BB.NUM_BALLS; i ++) {
            ball[i].delta();
            for(int y = (int) (ball[i].getY() - BB.BALL_RADIUS); y <= ball[i].getY() + BB.BALL_RADIUS; y++) {
                for (int x = (int) (ball[i].getX() - BB.BALL_RADIUS); x <= ball[i].getX() + BB.BALL_RADIUS; x++) {
                    int offset = (x + BB.X_SIZE * y) * BB.BPP;
                    byteBuffer[offset++] = (byte) 255;
                    byteBuffer[offset++] = (byte) 255;
                    byteBuffer[offset++] = (byte) 255;
                    byteBuffer[offset] = (byte) 255;
                }
            }
        }
        return byteBuffer;
    }
    private BBGfx() { throw new IllegalStateException("This is a utility class and should not be instantiated"); }
}
