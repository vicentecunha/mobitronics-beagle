package com.vcc.vicente.mobitronics;

import java.io.IOException;
import java.io.OutputStream;

public class SempraFrame {

    private SempraMode modoOperacao;
    private Channel canal1A;
    private Channel canal1B;
    private Channel canal2A;
    private Channel canal2B;
    private Channel canal3A;
    private Channel canal3B;
    private Channel canal4A;
    private Channel canal4B;
    private int checksum;

    private int genChecksum() {
        return 0; // TODO generate checksum
    }

    public SempraFrame(SempraMode sempraMode, Channel[] channels) {
        this.modoOperacao = sempraMode;
        this.canal1A = channels[0];
        this.canal1B = channels[1];
        this.canal2A = channels[2];
        this.canal2B = channels[3];
        this.canal3A = channels[4];
        this.canal3B = channels[5];
        this.canal4A = channels[6];
        this.canal4B = channels[7];
        this.checksum = genChecksum();
    }

    public void send(OutputStream outputStream) {

        byte[] bytesComando = {
                (byte) 0,
                (byte) modoOperacao.value
        };

        byte[] bytesCanal1A = canal1A.getBytes();
        byte[] bytesCanal1B = canal1B.getBytes();
        byte[] bytesCanal2A = canal2A.getBytes();
        byte[] bytesCanal2B = canal2B.getBytes();
        byte[] bytesCanal3A = canal3A.getBytes();
        byte[] bytesCanal3B = canal3B.getBytes();
        byte[] bytesCanal4A = canal4A.getBytes();
        byte[] bytesCanal4B = canal4B.getBytes();

        byte[] bytesCheckSum = {
                (byte) (checksum >> 8),
                (byte) checksum
        };

        try {
            outputStream.write(bytesComando);
            outputStream.write(bytesCanal1A);
            outputStream.write(bytesCanal1B);
            outputStream.write(bytesCanal2A);
            outputStream.write(bytesCanal2B);
            outputStream.write(bytesCanal3A);
            outputStream.write(bytesCanal3B);
            outputStream.write(bytesCanal4A);
            outputStream.write(bytesCanal4B);
            outputStream.write(bytesCheckSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
