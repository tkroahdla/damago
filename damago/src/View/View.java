package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.DamaDAO;
import Model.MemberDAO;


public class View {

	public static void main(String[] args) {
		
		DamaDAO dama = new DamaDAO();
		MemberDAO mem = new MemberDAO();
		int input = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("===== 다마고찌 키우기 게임 =====");
		System.out.println("1.로그인\n2.회원가입\n3.게임종료");
		System.out.println("==========================");
		int game_menu;
		int dama_menu;
		String id = null;
		int pw = 0;
		


		//mem.Login();
		mem.insertMember("제동현",123123);
		/*
		//메인 페이지 메뉴선택
=======
		if(input == 1) {
			//mem.Login();
		}
		else if(input ==2) {
			mem.insertMember("제동현",123123);
		}
		else {
			
		}
		
		
>>>>>>> branch 'master' of https://github.com/tkroahdla/damago
		while(true) {
			System.out.print("메뉴선택 >> ");
			game_menu = sc.nextInt();
			//User 로그인
			if(game_menu==1) {
				System.out.println("\n=====로 그 인=====");
				mem.Login(id, pw);

				System.out.println("1.다마고찌 등록\n2.다마고찌 관리\n3.다마고찌 랭킹");
				System.out.println("===========================");
			
					
/*					//다마고찌 관리 메뉴선택
					while(true) {
						System.out.print("메뉴선택 >> ");
						dama_menu = sc.nextInt();
								
						//다마고찌 등록
						if(dama_menu==1) {
							System.out.println("다마고찌 등록");
							System.out.println("등록할 다마고찌 정보를 입력하세요.");
							System.out.print("이름 : ");
							String dama_name = sc.next();
							System.out.print("종류 : "); // 속성은 어떤 종류가 있는걸로 할 것인지
							String dama_type = sc.next();
							
							boolean check3 = dama.insertdama(dama_name, dama_type);
							
							if(check3==true) {
								System.out.println("다마고찌 생성 성공");
							}else {
								System.out.println("다마고찌 생성 실패");
							}
							
						//다마고치 관리
						}else if(dama_menu==2) {
							System.out.println("다마고찌 관리");
							
							//내 다마고찌 현재상태(욕구충족상태)
							// 레벨, 이름, 종류, 에너지, 경험치 출력
							
							//DB에 등록된 다마고찌의 전체 조회가 아닌 현재 로그인한 유저의 다마고찌 상태를 출력 
							//현재 로그인한 유저의 다마고치의 정보를 가져오는 조건에 대한 조건작성은..잘모르겠음..
							System.out.println("레벨 : " + vo.getDamaLevel());
							System.out.println("이름 : " + vo.getDamaName());
							System.out.println("종류 : " + vo.getDamaType());
							System.out.println("에너지 : " + vo.getDamaEnergy());	
							System.out.println("경험치 : " + vo.getDamaExp());
							System.out.println();
							
							//다마고찌 행동선택
							while(true) {	
								System.out.println("1.밥먹기\n2.잠자기\n3.운동하기\n4.공부하기");
								int act_menu = sc.nextInt();
								
								if(act_menu==1) {
									System.out.println("밥을 먹는다!(포만감+10)"); 
									//(포만감+10)은 출력 예시, 밥을 먹으면 포만감이 얼마나 차는지,
									//에너지가 얼만큼 회복하는지, 다른 요소나 상태가 어떻게 얼만큼 바뀌는지도 정해야할듯.
									
									//욕구 충족해소의 경우, 욕구를 해소하기 위한 행동의 경우 다마고찌가 원하는 욕구이면
									//에너지가 증가하게, 욕구를 해소하지 않고 다른 행동을 하면 에너지가 감소하게끔 하는 조건도 설정
									
								}else if(act_menu==2) {
									System.out.println("잠을 잔다..");
								}else if(act_menu==3) {
									System.out.println("운동을 한다!");
								}else if(act_menu==4) {
									System.out.println("공부를 한다!");
								}else {
									System.out.println("잘못입력했습니다. 다시 입력해주세요.");
								}
							}
								
						//다마고찌 랭킹
						}else if(dama_menu==3) {
							System.out.println("다마고치 랭킹");		
							
							//DB에 등록된 다마고찌의 전체 조회가 아닌 현재 로그인한 유저의 다마고찌 상태를 출력 
							//현재 로그인한 유저의 다마고찌의 정보를 가져오는 조건에 대한 조건작성은..잘모르겠음..
							System.out.println("\n===========================");
							
							dama.selectDama();
							
							ArrayList<DamaVO> al = dama.selectDama();
							
							System.out.println();
							
							for(DamaVO vo : al) {
								System.out.println("1등 : " + vo.);
								System.out.println("2등 : " + vo.);
								System.out.println("4등 : " + vo.);
								System.out.println("5등 : " + vo.);	
							}
							System.out.println("===========================");
						}
					}
*/					

			//User 회원가입
			}else if(game_menu==2) {
				mem.insertMember(id, pw);
				boolean check2= true ;
				
				//User 회원가입 성공 
				if(check2==true) {
					System.out.println("회원가입 성공!!");
					
				//User 회원가입 실패	
				}else {
					System.out.println("회원가입 실패..");
				}
			
			//게임 종료
			}else if(game_menu==3) {
				System.out.println("게임 종료");
				break;
			}else {
				System.out.println("잘못입력했습니다. 다시 입력해주세요.");
			}
		
		}
		
	}


