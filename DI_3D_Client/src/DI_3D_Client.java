import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DI_3D_Client {
	public static void main(String[] args) {
		/* 소켓 변수 */
		Socket socket = null;
		try {
			/* 소켓 생성 */
			socket = new Socket();
			System.out.println("[연결 요청]");
			
			/* 입력 IP와 연결시도 "localhost는 차후 입력주어야하는 것으로 바꾸어야함." */
			socket.connect(new InetSocketAddress("localhost", 5001));
			System.out.println("[연결 성공]");

			/* 데이터받기 위한 바이트 변수 */
			byte[] bytes = null;
			
			/* 메시지 받을 변수 */
			String message = null;
			
			/* 데이터 받기 위한 InputStream 변수 */
			InputStream is = socket.getInputStream();
	
			/* 읽을 바이트 수 변수 값 설정 */
			bytes = new byte[100];
			
			/* InputStream으로 바이트수 만큼 읽은 데이터를 담음*/
			/* read를 호출시 , Server의 accept처럼 블로킹됨 */
			int readByteCount = is.read(bytes);
			
			/* 읽은 바이트를 UTF-8로 String 변환 */
			message = new String(bytes, 0, readByteCount, "UTF-8");
			
			/*받은 데이터 출력*/
			System.out.println("[데이터 받기 성공]: " + message);
			
			/*****************************/
			/* ★여기에 변환 알고리즘 함수 들어가야함 ★ */
			/* message = function(x)로 보내기위한 데이터 설정도 해주어야함*/
			/****************************/
			
			/* 데이터 보내기 위한 OutputStream 변수 */
			OutputStream os = socket.getOutputStream();
			
			/* 보내기 위한 데이터 설정*/
			message = "Hello Server";
			bytes = message.getBytes("UTF-8");
			/* 함수에서 리턴된 메시지를 바이트로 변환 */
			/* 이 부분도 function(x)에 들어가야함*/
			
			/*OutputStream 을 통하여 데이터를 전송함*/
			os.write(bytes);
			
			/* OutputStream을 비워줌 */
			os.flush();
			System.out.println("[데이터 보내기 성공]");

			

			os.close();
			is.close();
		} 
		catch (Exception e) {
		}

		if (!socket.isClosed()) {
			try {
				socket.close();
			} 
			catch (IOException e1) {
			}
		}
	}

}
