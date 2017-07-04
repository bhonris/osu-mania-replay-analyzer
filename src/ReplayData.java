public class ReplayData {
	public int offset;
	public Integer mode;
	public Integer version; 
	public String playerName;
	public ReplayData(byte[] byteData) {
		mode = (int) byteData[0];
		offset++;
		version =readInt(byteData);
		System.out.println(version.toString());
	}
	
	
	int readInt(byte[] byteData){
		//TODO: Interpreting version correctly
		return (byteData[offset]<<8)+(byteData[offset+1])+(byteData[offset+2]<<24)+(byteData[offset+3]<<16);
	}
	
	String readString(byte[] byteData, int offset){
		StringBuilder stringBuilder = new StringBuilder();
		while(byteData[offset]!=0x0b){
			stringBuilder.append(byteData[offset]);
			offset++;
		}
		return "";
	}

}
