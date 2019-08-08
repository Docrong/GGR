/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.common.security.crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: encrypt function using Base64 </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 *
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class Base64Encoder {

    public final static int BUFFER_SIZE = 1024;
    public final static byte[] ENCODE = {
            (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D',
            (byte) 'E', (byte) 'F', (byte) 'G', (byte) 'H', // 0-7
            (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L',
            (byte) 'M', (byte) 'N', (byte) 'O', (byte) 'P', // 8-15
            (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T',
            (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X', // 16-23
            (byte) 'Y', (byte) 'Z', (byte) 'a', (byte) 'b',
            (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f', // 24-31
            (byte) 'g', (byte) 'h', (byte) 'i', (byte) 'j',
            (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n', // 32-39
            (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r',
            (byte) 's', (byte) 't', (byte) 'u', (byte) 'v', // 40-47
            (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z',
            (byte) '0', (byte) '1', (byte) '2', (byte) '3', // 48-55
            (byte) '4', (byte) '5', (byte) '6', (byte) '7',
            (byte) '8', (byte) '9', (byte) '+', (byte) '/', // 56-63
            (byte) '=' // 64
    };

    /**
     * Description: encode data
     *
     * @param String input
     * @return String - encoded string
     * @throws IOException
     */
    public static final String encode(String input) throws IOException {
        byte bytes[];
        bytes = input.getBytes("ISO-8859-1");
        return encode(bytes);
    }

    /**
     * Description: encode data
     *
     * @param byte[] input
     * @return String - encoded string
     * @throws IOException
     */
    public static final String encode(byte[] input) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        process(in, out);
        return out.toString("ISO-8859-1");
    }

    /**
     * Description: encode the data in input stream, and output to the output stream
     *
     * @param InputStream  in - input data
     * @param OutputStream out - output data
     * @throws IOException
     */
    private static void process(InputStream in, OutputStream out) throws IOException {
        byte buffer[] = new byte[BUFFER_SIZE];
        int got = -1;
        int off = 0;
        int count = 0;
        while ((got = in.read(buffer, off, BUFFER_SIZE - off)) > 0) {
            if (got >= 3) {
                got += off;
                off = 0;
                while (off + 3 <= got) {
                    int c1 = get1(buffer, off);
                    int c2 = get2(buffer, off);
                    int c3 = get3(buffer, off);
                    int c4 = get4(buffer, off);
                    switch (count) {
                        case 73:
                            out.write(ENCODE[c1]);
                            out.write(ENCODE[c2]);
                            out.write(ENCODE[c3]);
                            out.write('\n');
                            out.write(ENCODE[c4]);
                            count = 1;
                            break;
                        case 74:
                            out.write(ENCODE[c1]);
                            out.write(ENCODE[c2]);
                            out.write('\n');
                            out.write(ENCODE[c3]);
                            out.write(ENCODE[c4]);
                            count = 2;
                            break;
                        case 75:
                            out.write(ENCODE[c1]);
                            out.write('\n');
                            out.write(ENCODE[c2]);
                            out.write(ENCODE[c3]);
                            out.write(ENCODE[c4]);
                            count = 3;
                            break;
                        case 76:
                            out.write('\n');
                            out.write(ENCODE[c1]);
                            out.write(ENCODE[c2]);
                            out.write(ENCODE[c3]);
                            out.write(ENCODE[c4]);
                            count = 4;
                            break;
                        default:
                            out.write(ENCODE[c1]);
                            out.write(ENCODE[c2]);
                            out.write(ENCODE[c3]);
                            out.write(ENCODE[c4]);
                            count += 4;
                            break;
                    }
                    off += 3;
                }
                // Copy remaining bytes to beginning of buffer:
                for (int i = 0; i < 3; i++) {
                    buffer[i] = (i < got - off) ? buffer[off + i] : ((byte) 0);
                }
                off = got - off;
            } else {
                // Total read amount is less then 3 bytes:
                off += got;
            }
        }
        // Manage the last bytes, from 0 to off:
        switch (off) {
            case 1:
                out.write(ENCODE[get1(buffer, 0)]);
                out.write(ENCODE[get2(buffer, 0)]);
                out.write('=');
                out.write('=');
                break;
            case 2:
                out.write(ENCODE[get1(buffer, 0)]);
                out.write(ENCODE[get2(buffer, 0)]);
                out.write(ENCODE[get3(buffer, 0)]);
                out.write('=');
        }
        return;
    }

    private static int get1(byte buf[], int off) {
        return (buf[off] & 0xfc) >> 2;
    }

    private static int get2(byte buf[], int off) {
        return ((buf[off] & 0x3) << 4) | ((buf[off + 1] & 0xf0) >>> 4);
    }

    private static int get3(byte buf[], int off) {
        return ((buf[off + 1] & 0x0f) << 2) | ((buf[off + 2] & 0xc0) >>> 6);
    }

    private static int get4(byte buf[], int off) {
        return buf[off + 2] & 0x3f;
    }

    /*Only for testing */
    public static void main(String[] args) {
        Base64Encoder inst = new Base64Encoder();
        String testStr = "password";
        byte[] testChar = "password".getBytes();

        try {
            System.out.println(Base64Encoder.encode(testStr));
            System.out.println(Base64Encoder.encode(testChar));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
