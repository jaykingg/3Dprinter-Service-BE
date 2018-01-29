import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DI_3D_Server {
	public static void main(String[] args) {
		/*소켓 변수*/
		ServerSocket serverSocket = null;
		try {
			/* 서버 소켓 생성 */
			serverSocket = new ServerSocket();
			/* 소켓과 포트변호 바인드 */
			serverSocket.bind(new InetSocketAddress("localhost", 5001));
			while (true) {
				System.out.println("[연결 기다림]");
				
				/* 소켓 accept, 블록상태 */
				Socket socket = serverSocket.accept();
				
				/* 연결된 ip받아옴 */
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[연결 수락함] " + isa.getHostName());
				
				/* byte변수 */
				byte[] bytes = null;
				/* 메시지담을 String 변수 */
				String message = null;

				/* 클라이언트로부터 inputStream을 받아올 변수 */
				InputStream is = socket.getInputStream();
				bytes = new byte[100];
				
				/* 바이트만큼 데이터 InputStream에서 데이터 읽어옴 */
				int readByteCount = is.read(bytes);
				
				/* UTF-8타입으로 읽어온데이터를 String으로 변환함 */
				message = new String(bytes, 0, readByteCount, "UTF-8");
				
				/* 받았던 데이터 출력 */
				System.out.println("[데이터 받기 성공]: " + message);
				
				/* 클라이언트로 데이터를 보내기 위한 socket에 대한 OutputStream 변수 */
				OutputStream os = socket.getOutputStream();
				
				/* 임의의 메시지 변수 */
				message = "Hello Client";
				
				/*매시지를 바이트로 바꿈 */
				bytes = message.getBytes("UTF-8");
				
				/* OutputStream으로 메시지를 보냄 */
				os.write(bytes);
				
				/* OutputStream을 비움 */
				os.flush();
				
				/* 보내기 성공 메시지 출력 */
				System.out.println("[데이터 보내기 성공]");

				is.close();
				os.close();
				socket.close();
			}
		} 
		catch (Exception e) {
		}

		if (!serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} 
			catch (IOException e1) {
			}
		}
	}

}