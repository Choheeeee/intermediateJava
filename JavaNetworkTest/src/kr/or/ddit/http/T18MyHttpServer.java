package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;


//Application레이어의 http프로토콜을 쓴다는것은 transport레이어의 tcp까지 기본으로 깔고 있는것!
public class T18MyHttpServer {
	
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	
	public void start() {
		
		//start()를 실행하면, ServerSocket을 생성하고 ServerSocket은 accept()로 수신을 대기하고 있다가
		//수신 연결이 되면, 새 socket에 상대방을 연결해줌
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(this.port);
			
			while (true) {
				System.out.println("브라우저의 요청을 대기중입니다!");
				Socket socket = serverSocket.accept();
				
				//Http 요청 처리 스레드 생성 및 구동
				HttpHandler handler = new HttpHandler(socket);
				handler.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//브라우저가 server에 요청을 하면, 그 요청에 응답을 주는 역할을 하는 스레드
	class HttpHandler extends Thread {
		private Socket socket;
		
		//매개변수로 socket을 넣음
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		

		@Override
		public void run() {
			
			System.out.println("서버가 브라우저가 보낸 요청에  대해 처리 시작!");
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				//브라우저에게 응답을 보내주려고 출력스트림 객체 준비
				out = new BufferedOutputStream(socket.getOutputStream());
				
				//브라우저가 서버에 보낸 요청이 문자열이라는걸 알고, 바이트기반을 문자기반으로 바꿔주는 입력 보조스트림 이용하고, 효율적으로 읽으려고 BufferedReadear로 랩핑
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				//요청중 헤더 정보 파싱하기
				StringBuilder sb = new StringBuilder();
				
				//Request Line 읽기
				String reqLine = br.readLine();
				
				//읽은 reqLine 출력해보기
//				System.out.println("Request Line : "+ reqLine);
				
				while (true) {
					//헤더의 첫번째줄 읽기
					String str = br.readLine();
					
					//(Get방식엔 Body가 없음)
					if (str.equals("")) break;	//Empty Line이 들어가 있는지 아닌지 체크하기(엔터 두번이 포함돼 있는지)
					
					//헤더의 두번째줄부터 읽을때마다 개행
					sb.append(str+ "\n");
				}
				
				//헤더 정보(클라이언트가 실제로  원하는건 index.html이므로 이걸 추출해야함)
//				String reqHeader = sb.toString();
//				System.out.println("=== 요청 헤더 정보 === ");
//				System.out.println(reqHeader);
//				System.out.println("--------------------------------------------------------------");
				
				//요청 메세지의 경로정보 가져오기
				String reqPath = "";	//요청 경로정보를 담기위한 변수 선언
				
				//공백을 구분지로해서 문자열을 쪼갬
				StringTokenizer st = new StringTokenizer(reqLine);
				
				//읽을 다음 토큰이 있을때까지 다음으로 넘어감(브라우저에 응답을 돌려주기위해, http프로토콜 형식의 파일 만들기 - statsLine, Empty Line, Body)
				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					
					//쪼개서 읽은 토큰중 "/"로 시작하는 토큰이 있으면 reqPath에 토큰을 저장하고 멈추기
					if (token.startsWith("/")) {
						reqPath = token;
						break;
					}
				}
				
//				System.out.println("요청 경로 정보 : "+reqPath);
				
				//한글은 이상하게 나오니까 URL디코더를 이용한 경로정보 디코딩 셋팅하기
				reqPath = URLDecoder.decode(reqPath, encoding);	//위에서 만들어놓은 인코딩셋팅할때 이용한 변수(UTF-8)를 활용해서 한글로 복원하는 작업을 함.
				
				//요청경로 정보를 확인했으니, 서버는 http프로토콜로 다시 client에 응답을 쏴주기
				String filePath = "./WebContent" + reqPath;
				
				//해당 파일이름을 이용하여 Content-Type 정보 추출하기
				//(마임타입 - text파일형식인데 구체적으로 text파일중 html파일임 / ex : image/png, jpg ...)
				//마임타입 : body부분 안에 들어있는 컨텐츠가 뭔지 더 구체적으로 알려주기위해 사용
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
				
				File file = new File(filePath);
				if (!file.exists()) {
					
					//Not Found페이지를 띄우기위해 메서드 호출
					makeErrorPage(out, 404, "Not Found");
					return;
				}
				
				//응답할 내용을 바이트 배열에 담음
				byte[] body = makeResponseBody(filePath);
				
				byte[] header = makeResponseHeader(body.length, contentType);
				
				
				//서버가 header와 body에 응답내용을 준비했으면 이제 브라우저에게 쏴주기
				out.write(header);
				
				//응답 내용을 보내기전에, 반드시 header와 body를 구분해주는 Empty Line도 표현하기
				// "\r\n" 이렇게 한쌍이 엔터1번의 의미 => Empty Line표현을 위해 엔터 2번 친 의미를 표현함
				out.write("\r\n\r\n".getBytes());
				out.write(body);
				
				out.flush();	//버퍼는 버퍼가 다 찰때까지를 기다려 데이터를 보내는데, 다 차기 전에 강제로 데이터를 내보내서 모든 데이터를 보냄
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		

		/**
		 * 응답메세지중 헤더에 에러메세지를 출력하는 메세지(에러메세지일땐 바디  필요 x)
		 * @param out
		 * @param statusCode
		 * @param errMsg
		 */
		private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
			
			String statusLine = "HTTP/1.1" + " " + statusCode + " "+errMsg;
			
			try {
				out.write(statusLine.getBytes());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					out.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			
		}


		
		
		/**
		 * 응답메세지의 Body부분 내용 채우기
		 * @param filePath 응답 내용으로 사용할 파일 경로
		 * @return 브라우저에게 보낼 응답내용을 담은 바이트 배열
		 */
		private byte[] makeResponseBody(String filePath) {
			
			byte[] data = null;	//결국 data라는 변수가 담은 내용은 body부분에 표현하고 싶은 내용임.
			
			FileInputStream fis = null;
			
			try {
				//파일 사이즈가 궁금해서, 파일객체 만들고 파일객체의 length이용해서 사이즈 확인해보기
				File file = new File(filePath);
				data = new byte[(int)file.length()];	//파일 사이즈를 확인후, 파일 사이즈만큼의 바이트배열을 만듦
				
				fis = new FileInputStream(file);
				fis.read(data);
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			return data;
		}
	}
	
	
	
	/**
	 * 서버가 브라우저에 응답할  응답메세지의 헤더부분 내용 생성하기
	 * @param contentLength 응답내용 크기
	 * @param mimeType 응답내용 컨텐츠 타입정보 = mime타입
	 * @return 헤더정보를 담은 바이트 배열
	 */
	private byte[] makeResponseHeader(int contentLength, String mimeType) {

		//헤더 젤 첫줄엔 status Line이 와야함
		String header = "HTTP/1.1 200 OK\r\n" 
					+ "Server: MyHttpServer 1.0\r\n"
					+ "Content-length: " + contentLength + "\r\n"
					+ "Content-type: "+mimeType + "charset = "+this.encoding;
		
		return header.getBytes();
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		new T18MyHttpServer().start();
	}

}
