package org.librespot.spotify.crypto;

import org.jetbrains.annotations.Nullable;

/**
 * @author Gianlu
 */
public class Packet {
    public final byte cmd;
    public final byte[] payload;

    Packet(byte cmd, byte[] payload) {
        this.cmd = cmd;
        this.payload = payload;
    }

    @Nullable
    public Type type() {
        return Type.parse(cmd);
    }

    public enum Type {
        SecretBlock(0x02),
        Ping(0x04),
        StreamChunk(0x08),
        StreamChunkRes(0x09),
        ChannelError(0x0a),
        ChannelAbort(0x0b),
        RequestKey(0x0c),
        AesKey(0x0d),
        AesKeyError(0x0e),
        Image(0x19),
        CountryCode(0x1b),
        Pong(0x49),
        PongAck(0x4a),
        Pause(0x4b),
        ProductInfo(0x50),
        LegacyWelcome(0x69),
        LicenseVersion(0x76),
        Login(0xab),
        APWelcome(0xac),
        AuthFailure(0xad),
        MercuryReq(0xb2),
        MercurySub(0xb3),
        MercuryUnsub(0xb4),
        UnknownData_AllZeros(0x1f);

        public final byte val;

        Type(int val) {
            this.val = (byte) val;
        }

        @Nullable
        public static Packet.Type parse(byte val) {
            for (Type cmd : values())
                if (cmd.val == val)
                    return cmd;

            return null;
        }
    }
}
