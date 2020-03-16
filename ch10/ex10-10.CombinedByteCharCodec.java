// 예제 10.10 CombineedChannelDupliexHandler<I,O>

public class CombinedByteCharCodec extends
    CombineedChannelDupliexHandler<ByteToCharDecoder, CharToByteEncoder> {
    public CombinedByteCharCodec() {
	super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
