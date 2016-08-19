//このソースは、VstoneMagicによって自動生成されました。
//ソースの内容を書き換えた場合、VstoneMagicで開けなくなる場合があります。
package	jp.co.mysota;
import	main.main.GlobalVariable;
import	jp.vstone.RobotLib.*;
import	jp.vstone.sotatalk.*;
import	jp.vstone.sotatalk.SpeechRecog.*;
import	java.io.*;

public class TalkChecker
{

	public String speechRecogResult;
	public RecogResult recogresult;
	public jp.co.mysota.WebsocketMessenger WebsocketMessenger;
	public TalkChecker()																								//@<BlockInfo>jp.vstone.block.func.constructor,16,16,496,16,False,3,@</BlockInfo>
	{
																														//@<OutputChild>
		/*String speechRecogResult*/;																					//@<BlockInfo>jp.vstone.block.variable,80,16,80,16,False,2,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*RecogResult recogresult*/;																					//@<BlockInfo>jp.vstone.block.variable,144,16,144,16,False,1,break@</BlockInfo>
																														//@<EndOfBlock/>
		WebsocketMessenger=new jp.co.mysota.WebsocketMessenger();														//@<BlockInfo>jp.vstone.block.variable,208,16,208,16,False,9,break@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void talkCheck()																								//@<BlockInfo>jp.vstone.block.func,64,192,576,192,False,8,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		WebsocketMessenger.connect((String)"");																			//@<BlockInfo>jp.vstone.block.callfunc.base,128,192,128,192,False,7,@</BlockInfo>	@<EndOfBlock/>
		while(GlobalVariable.TRUE)																						//@<BlockInfo>jp.vstone.block.while.endless,288,192,480,192,False,6,Endless@</BlockInfo>
		{

																														//@<OutputChild>
			speechRecogResult = GlobalVariable.recog.getResponsewithAbort((int)10000,(int)3);							//@<BlockInfo>jp.vstone.block.talk.speechrecog.get,352,192,352,192,False,5,音声認識して、得られた結果（文字列）をspeechRecogResultに代入します。@</BlockInfo>
			if(speechRecogResult == null) speechRecogResult = "";

																														//@<EndOfBlock/>
			WebsocketMessenger.sendMessage((String)speechRecogResult);													//@<BlockInfo>jp.vstone.block.callfunc.base,416,192,416,192,False,4,@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>
		}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

}
