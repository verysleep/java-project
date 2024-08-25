package com.SedEx.member.controller;

import java.util.Calendar;
import java.util.List;

import com.SedEx.main.controller.Main;
import com.SedEx.member.db.LoginVO;
import com.SedEx.member.db.MemberVO;
import com.SedEx.member.service.MemberDeleteService;
import com.SedEx.member.service.MemberFindIDService;
import com.SedEx.member.service.MemberFindPWService;
import com.SedEx.member.service.MemberListService;
import com.SedEx.member.service.MemberLoginService;
import com.SedEx.member.service.MemberAdminUpdateService;
import com.SedEx.member.service.MemberAuthUpdateService;
import com.SedEx.member.service.MemberStatusUpdateService;
import com.SedEx.member.service.MemberUserUpdateService;
import com.SedEx.member.service.MemberViewService;
import com.SedEx.member.service.MemberWriteService;
import com.SedEx.util.exe.Execute;
import com.SedEx.util.io.In;
import com.SedEx.util.io.MemberPrint;

public class MemberController {

	public void execute() {

		// 받을 데이터 변수 선언
		Object result = null;
		String id = null;
		String pw = null;
		String menu;

		while (true) {
			// 로그인이 되어 있지 않은 경우
			if (Main.login == null) {
				System.out.println();
				System.out.println("================================================================");
				System.out.println("========= 현재 비회원 접속 중입니다. 로그인 또는 회원 가입을 해주세요. =========");
				System.out.println("================================================================");
				System.out.println(" 1. 회원 가입   2. 로그인   3. 아이디 찾기   4. 비밀번호 찾기   0. 이전메뉴");
				System.out.println("================================================================");

				menu = In.getStr(" ▶ 회원 메뉴");

				try {
					switch (menu) {
					// 비회원 - 1. 회원가입
					case "1":
						System.out.println(" ▶ 1. 회원가입");
						// 데이터 수집
						id = In.getStr(" ▷ ID");
						pw = In.getStr(" ▷ PW");
						String name = In.getStr(" ▷ 이름");
						String gender = In.getStr(" ▷ 성별(남/여)");
						String birth = In.getStr(" ▷ 생년월일");
						String tel = In.getStr(" ▷ 연락처");
						String email = In.getStr(" ▷ 이메일");
						String address = In.getStr(" ▷ 주소");

						// 만 14세 미만 확인
						Calendar c = Calendar.getInstance();
						int birth1 = Integer.parseInt(birth.substring(0, 4));
						int year = c.get(Calendar.YEAR);
						int result1 = year - birth1 - 1;
//						System.out.println(result1);
						if (result1 >= 14) {

							// 회원가입 DB 처리
							MemberVO vo = new MemberVO();
							vo.setId(id);
							vo.setPw(pw);
							vo.setName(name);
							vo.setGender(gender);
							vo.setBirth(birth);
							vo.setTel(tel);
							vo.setEmail(email);
							vo.setAddress(address);

							result = Execute.execute(new MemberWriteService(), vo);
							System.out.println(" ▶ 회원 등록이 정상 처리 되었습니다.");
							System.out.println(" ▶ 가입한 아이디로 로그인해 주세요.");

							break;
						} else {
							System.out.println(" ▶ 만 14세 미만은 가입할 수 없습니다.");
							break;
						}

						// 비회원 - 2. 로그인
					case "2":
						System.out.println(" ▶ 2. 로그인");
						if (Main.login == null) {
							// 데이터 수집 - 로그인
							id = In.getStr(" ▷ ID");
							pw = In.getStr(" ▷ PW");
							LoginVO loginVO = new LoginVO();
							loginVO.setId(id);
							loginVO.setPw(pw);

							// 로그인 DB 처리
							Main.login = (LoginVO) Execute.execute(new MemberLoginService(), loginVO);
							System.out.println(" ▶ 정상적으로 로그인 되었습니다.");
						}
//						else {
//							// 로그아웃 처리
//							Main.login = null;
//							System.out.println(" ▶ 정상적으로 로그아웃 되었습니다.");
//						}

						break;

					// 아이디 찾기
					case "3":
						System.out.println(" ▶ 3. 아이디 찾기");
						System.out.println(" ▶ 가입한 이름과 이메일로 찾을 수 있습니다.");

						// 데이터 수집
						name = In.getStr(" ▷ 회원 이름");
						email = In.getStr(" ▷ 회원 이메일");
						MemberVO memberVO = new MemberVO();
						memberVO.setName(name);
						memberVO.setEmail(email);

						// DB 처리
						result = Execute.execute(new MemberFindIDService(), memberVO);
						if (result == null) {
							System.out.println("@@@ 입력하신 정보가 맞지 않습니다. 다시 확인해 주세요.");
						} else {
							new MemberPrint().findID(memberVO);
						}

						break;

					// 비밀번호 찾기
					case "4":
						System.out.println(" ▶ 4. 비밀번호 찾기");
						// 데이터 수집
						id = In.getStr(" ▷ ID");
						email = In.getStr(" ▷ Email");
						String updatePw = In.getStr(" ▷ 변경할 비밀번호 입력");
						memberVO = new MemberVO();
						memberVO.setId(id);
						memberVO.setEmail(email);
						memberVO.setPw(updatePw);

						// DB 처리
						result = Execute.execute(new MemberFindPWService(), memberVO);
						System.out.println(" ▶ 비밀번호 변경이 정상 처리 되었습니다.");
						System.out.println(" ▶ 변경된 비밀번호로 다시 로그인 해 주세요.");
						Main.login = null;

						break;

					case "0":
						System.out.println(" ▶ 이전 메뉴로 돌아갑니다.");

						return;

					default:
						System.out.println("@@@ 오류 : 잘못된 접근입니다. 메인메뉴로 돌아갑니다. @@@");
						break;
					} // end of switch
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("@@@ 오류 발생 : 정보가 일치하지 않습니다.");
//						throw e;
//					else
//						throw new Exception("에외 발생 : 로그인 처리 중 예외가 발생했습니다.");
				} // end of try ~ catch
			} // end of if(비회원)

			// 로그인 되었을 경우
			if (Main.login != null) {
				try {
					// 회원 등급 확인
					switch ((int) Main.login.getGradeNo()) {

					// 관리자 메뉴 진입
					case 0:
						System.out.println();
						System.out.println("================================================================");
						System.out.println("====================== 관리자로 접속 하셨습니다. ======================");
						System.out.println("================================================================");
						System.out.println(" 1. 회원 리스트     2. 회원 정보 보기    3. 회원 등록       4. 회원 정보 수정");
						System.out.println(" 5. 회원 아이디 찾기 6. 회원 비밀번호 찾기  7. 회원 권한 변경   8. 회원 삭제");
						System.out.println(" 9. 로그 아웃      0. 이전 메뉴");
						System.out.println("================================================================");

						switch (menu = In.getStr(" ▶ 관리자 메뉴")) {

						// 관리자 회원리스트 - 회원 전체 리스트
						case "1":
							System.out.println(" ▶ 1. 회원리스트");
							// DB 에서 데이터 가져오기
							result = Execute.execute(new MemberListService(), null);
							// 가져온 데이터 출력
							new MemberPrint().print((List<MemberVO>) result);
							break;

						// 관리자 회원 정보 보기 - 아이디 입력 받아 정보 출력
						case "2":
							System.out.println(" ▶ 2. 회원 정보 보기");

							id = In.getStr("ID");
							// DB 에서 데이터 가져오기
							result = Execute.execute(new MemberViewService(), id);
							new MemberPrint().print((MemberVO) result);

							break;

						// 관리자 권한으로 회원 등록 - 만 14세 미만은 가입 불가능
						case "3":
							System.out.println(" ▶ 3. 회원 등록");
							// 데이터 수집
							id = In.getStr(" ▷ ID");
							pw = In.getStr(" ▷ PW");
							String name = In.getStr(" ▷ 이름");
							String gender = In.getStr(" ▷ 성별(남/여)");
							String birth = In.getStr(" ▷ 생년월일");
							String tel = In.getStr(" ▷ 연락처");
							String email = In.getStr(" ▷ 이메일");
							String address = In.getStr(" ▷ 주소");

							// 만 14세 미만 확인
							Calendar c = Calendar.getInstance();
							int birth1 = Integer.parseInt(birth.substring(0, 4));
							int year = c.get(Calendar.YEAR);
							int result1 = year - birth1 - 1;
//							System.out.println(result1);
							if (result1 >= 14) {

								// DB 처리
								MemberVO vo = new MemberVO();
								vo.setId(id);
								vo.setPw(pw);
								vo.setName(name);
								vo.setGender(gender);
								vo.setBirth(birth);
								vo.setTel(tel);
								vo.setEmail(email);
								vo.setAddress(address);

								result = Execute.execute(new MemberWriteService(), vo);
								System.out.println(" ▶ 회원 등록이 정상 처리 되었습니다.");

								break;
							} else {
								System.out.println(" ▶ 만 14세 미만은 가입할 수 없습니다.");
								break;
							}

							// 관리자 권한 회원 정보 수정 - 수정할 아이디 입력하여 수정 가능 (비밀번호 필요없음)
						case "4":
							System.out.println(" ▶ 4. 회원 정보 수정");

							// 수정할 데이터 가져오기
							id = In.getStr(" ▷ 수정할 아이디 입력");
							MemberVO vo = (MemberVO) Execute.execute(new MemberViewService(), id);

							// 데이터 확인
							if (vo == null) {
								throw new Exception("예외 발생 : 입력하신 아이디는 존재하지 않습니다.");
							}

							// 정상적인 아이디 확인
							whileLoop: while (true) {
								new MemberPrint().print(vo);
								System.out.println();
								System.out
										.println("-------------------------------------------------------------------");
								System.out.println("  1. 비밀번호    2. 회원 이름   3. 회원 생년월일   4. 회원 연락처   5. 회원 주소 ");
								System.out.println("  6. 회원 사진   7. 회원 상태   9. 수정 취소      0. 수정 완료");
								System.out
										.println("-------------------------------------------------------------------");
								String item = In.getStr(" ▷ 수정 항목 선택");

								switch (item) {
								case "1": {
									vo.setPw(In.getStr(" ▷ 비밀번호 변경"));
									break;
								}
								case "2": {
									vo.setName(In.getStr(" ▷ 회원 이름 변경"));
									break;
								}
								case "3": {
									vo.setBirth(In.getStr(" ▷ 생년월일 번경"));
									break;
								}
								case "4": {
									vo.setTel(In.getStr(" ▷ 회원 연락처 변경"));
									break;
								}
								case "5": {
									vo.setAddress(In.getStr(" ▷ 회원 주소 변경"));
									break;
								}
								case "6": {
									vo.setPhoto(In.getStr(" ▷ 회원 사진 변경"));
									break;
								}
								case "7": {
									vo.setStatus(In.getStr(" ▷ 회원 상태 변경 (정상/제재/탈퇴/휴면)"));
									break;
								}
								case "9": {
									System.out.println("");
									System.out.println("*** 수정이 취소 되었습니다. ***");
									break whileLoop;
								}
								case "0": {
									if (Main.login.getGradeNo() == 0)
										// DB 적용하는 처리문 작성. NoticeUpdateService
										Execute.execute(new MemberAdminUpdateService(), vo);
									System.out.println("*** 회원 정보를 수정 완료했습니다. ***");
									break whileLoop;
								}

								default:
									System.out.println();
									System.out.println("***********************************");
									System.out.println("**** 잘못된 항목 번호를 선택하셨습니다. ****");
									System.out.println(" [1 ~ 7, 9, 0] 번호를 선택하여야 합니다. ");
									System.out.println("***********************************");
									break;
								} // end of update switch
							} // end of update while
							break;

						// 아이디 찾기 - 회원, 관리자 모두 가입한 이름과 이메일로 찾을 수 있다.
						case "5":
							System.out.println(" ▶ 5. 회원 아이디 찾기");
							System.out.println(" ▶ 회원 이름과 이메일을 입력해 주세요.");
							// 데이터 수집
							name = In.getStr(" ▷ 회원 이름");
							email = In.getStr(" ▷ 회원 이메일");
							MemberVO memberVO = new MemberVO();
							memberVO.setName(name);
							memberVO.setEmail(email);

							// DB 처리
							result = Execute.execute(new MemberFindIDService(), memberVO);
							if (result == null) {
								System.out.println("@@@ 입력하신 정보가 맞지 않습니다. 다시 확인해 주세요.");
							} else {
								new MemberPrint().findID(memberVO);
							}
							break;

						// 비밀번호 찾기 > 변경 - 회원, 관리자 모두 가입한 아이디와 이메일을 확인하여 비밀번호를 변경한다.
						case "6":
							System.out.println(" ▶ 6. 회원 비밀번호 변경");
							System.out.println(" ▶ 등록한 아이디와 이메일로 비밀번호를 변경할 수 있습니다.");
							// 데이터 수집
							id = In.getStr(" ▷ ID");
							email = In.getStr(" ▷ Email");
							pw = In.getStr(" ▷ 변경할 비밀번호 입력");
							memberVO = new MemberVO();
							memberVO.setId(id);
							memberVO.setEmail(email);
							memberVO.setPw(pw);

							// DB 처리
							result = Execute.execute(new MemberFindPWService(), memberVO);
							System.out.println(" ▶ 비밀번호 변경이 정상 처리 되었습니다.");
							break;

						// 회원 권한 변경
						case "7":
							System.out.println(" ▶ 7. 회원 권한 변경");
							// DB 수집
							id = In.getStr("권한 변경할 ID");
							int gradeNo = Integer.parseInt(In.getStr(" ▷ 처리할 권한 ( 1 : 일반회원 / 2 : 쇼핑몰 관리자 )"));
							memberVO = new MemberVO();
							memberVO.setId(id);
							memberVO.setGradeNo(gradeNo);

							// DB 처리
							result = Execute.execute(new MemberAuthUpdateService(), memberVO);
							System.out.println(" ▶ 회원 권한이 정상 처리 되었습니다.");

							break;

						// 회원 상태 -> 데이터 완전 삭제
						case "8":
							System.out.println(" ▶ 8. 회원 삭제");
							// 데이터 수집
							id = In.getStr(" ▷ 삭제할 아이디 입력");
							memberVO = new MemberVO();
							memberVO.setId(id);

							// DB 처리
							result = Execute.execute(new MemberDeleteService(), memberVO);
							
							if ((int)result != 0) {
							System.out.println(" ▶ " + memberVO.getId() + " 님이 삭제 되었습니다.");
							} else {
								System.out.println(" ▶ 입력하신 아이디가 존재하지 않습니다.");
							}

							break;

						// 로그아웃 - 비회원 메뉴로 넘어간다.
						case "9":
							System.out.println(" ▶ 9. 로그아웃");

							if (Main.login != null)
								Main.login = null;
							System.out.println(" ▶ 정상적으로 로그아웃 되었습니다.");
							System.out.println();
							break;

						// 메인 메뉴로 넘어간다.
						case "0":
							System.out.println(" ▶ 이전 메뉴로 돌아갑니다.");
							System.out.println();
							return;

						default:
							System.out.println("@@@ 잘못된 메뉴를 입력하셨습니다. [1~9, 0] 중에 입력하세요.");
							System.out.println();
							break;
						} // end of 관리자 메뉴
						break;

					// 일반회원 메뉴 진입
					case 1:
						System.out.println();
						System.out.println("================================================================");
						System.out.println("=================== 일반회원으로 접속 하셨습니다. ======================");
						System.out.println("================================================================");
						System.out.println("1. 내 정보 보기    2. 내 정보 수정      3. 로그아웃        4. 회원 탈퇴");
						System.out.println("0. 이전 메뉴");
						System.out.println("================================================================");

						switch (menu = In.getStr(" ▶ 일반회원 메뉴")) {

						// 현재 로그인 되어 있는 아이디에 상세정보
						case "1":
							System.out.println(" ▶ 1. " + Main.login.getId() + "님 상세 정보");

							id = Main.login.getId();
							// DB 에서 데이터 가져오기
							result = Execute.execute(new MemberViewService(), id);
							new MemberPrint().print((MemberVO) result);

							break;

						// 수정할 정보를 선택하여 수정한다.
						case "2":
							System.out.println(" ▶ 2. 회원 정보 수정");

							// 수정할 데이터 가져오기
							id = Main.login.getId();
							MemberVO vo = (MemberVO) Execute.execute(new MemberViewService(), id);

							// 수정할 데이터 선택
							whileLoop: while (true) {
								new MemberPrint().print(vo);
								System.out.println();
								System.out
										.println("-------------------------------------------------------------------");
								System.out.println("  1. 비밀번호    2. 회원 이름   3. 회원 생년월일   4. 회원 연락처   5. 회원 주소 ");
								System.out.println("  6. 회원 사진   9. 수정 취소   0. 수정 완료");
								System.out
										.println("-------------------------------------------------------------------");
								String item = In.getStr(" ▷ 수정 항목 선택");

								switch (item) {
								case "1": {
									vo.setPw(In.getStr(" ▷ 비밀번호 변경"));
									break;
								}
								case "2": {
									vo.setName(In.getStr(" ▷ 회원 이름 변경"));
									break;
								}
								case "3": {
									vo.setBirth(In.getStr(" ▷ 생년월일 번경"));
									break;
								}
								case "4": {
									vo.setTel(In.getStr(" ▷ 회원 연락처 변경"));
									break;
								}
								case "5": {
									vo.setAddress(In.getStr(" ▷ 회원 주소 변경"));
									break;
								}
								case "6": {
									vo.setPhoto(In.getStr(" ▷ 회원 사진 변경"));
									break;
								}
								case "9": {
									System.out.println("");
									System.out.println("*** 수정이 취소 되었습니다. ***");
									break whileLoop;
								}
								case "0": {
//									vo.setPw(In.getStr(" ▷ 변경하려면 기존 비밀번호를 입력하세요."));
									Execute.execute(new MemberUserUpdateService(), vo);
									System.out.println("*** 회원 정보를 수정 완료했습니다. ***");
									break whileLoop;
								}

								default:
									System.out.println();
									System.out.println("***********************************");
									System.out.println("**** 잘못된 항목 번호를 선택하셨습니다. ****");
									System.out.println(" [1 ~ 6, 9, 0] 번호를 선택하여야 합니다. ");
									System.out.println("***********************************");
									break;
								} // end of update switch
							} // end of update while
							break;

						// 로그아웃 - 비회원 메뉴로 진입한다.
						case "3":
							System.out.println(" ▶ 3. 로그아웃");

							if (Main.login != null)
								Main.login = null;
							System.out.println(" ▶ 정상적으로 로그아웃 되었습니다.");
							break;

						// 회원 상태 -> 탈퇴로 변경 처리
						case "4":
							System.out.println(" ▶ 4. 회원 탈퇴");
							System.out.println(" ▶ 탈퇴하려면 등록하신 이메일 주소와 비밀번호를 입력하세요.");

							vo = new MemberVO();
							String answer = null;

							// 데이터 수집
							whileLoop: while (true) {
								vo.setEmail(In.getStr(" ▷ 이메일"));
								vo.setPw(In.getStr(" ▷ 비밀번호"));
								System.out.println();
								answer = In.getStr(" ▷ 정말 탈퇴하시겠습니까? 탈퇴하면 기존에 사용하였던 아이디와 이메일 주소는 사용할 수 없습니다. (y/n)");

								switch (answer) {
								case "y":
									result = Execute.execute(new MemberStatusUpdateService(), vo);
									if ((int) result == 1) {

										System.out.println(" ▶ 회원 탈퇴가 정상 처리 되었습니다.");
										System.out.println();
										Main.login = null;
									} else {
										System.out.println(" ▶ 입력하신 회원정보가 일치하지 않습니다. 다시 입력해주세요.");
										System.out.println();
										System.out.println(" ▶ 회원 탈퇴");
										break;
									}
									break whileLoop;

								case "n":
									System.out.println(" ▶ 이전 메뉴로 돌아갑니다.");
									break whileLoop;

								default:
									System.out.println("@@@ 잘못 입력 하셨습니다. 탈퇴하려면 y / 취소하려면 n 를 입력해 주세요. @@@");
									break;
								} // end of delete switch
							} // end of delete while
							break;

						case "0":
							System.out.println(" ▶ 이전 메뉴로 돌아갑니다.");
							System.out.println();
							return;

						default:
							System.out.println("@@@ 잘못된 메뉴를 입력하셨습니다. [1 ~ 4, 0] 중에 입력하세요. @@@");
							System.out.println();
							break;
						}
						break; // end of 일반회원 메뉴

					// 쇼핑몰 관리자 메뉴 진입
					case 2:
						System.out.println();
						System.out.println("================================================================");
						System.out.println("================== 쇼핑몰 관리자로 접속 하셨습니다. =====================");
						System.out.println("================================================================");
						System.out.println("1. 쇼핑몰 정보 보기  2. 쇼핑몰 정보 수정  3. 로그아웃        0. 이전메뉴");
						System.out.println("================================================================");

						switch (menu = In.getStr(" ▶ 쇼핑몰 메뉴")) {
						// 현재 로그인 되어 있는 아이디의 상세정보
						case "1":
							System.out.println(" ▶ 1. " + Main.login.getId() + "님 상세 정보");

							id = Main.login.getId();
							// DB 에서 데이터 가져오기
							result = Execute.execute(new MemberViewService(), id);
							new MemberPrint().print((MemberVO) result);

							break;

						// 현재 로그인 되어 있는 아이디의 정보 변경
						case "2":
							System.out.println(" ▶ 2. 쇼핑몰 정보 수정");

							// 수정할 데이터 가져오기
							id = Main.login.getId();
							MemberVO vo = (MemberVO) Execute.execute(new MemberViewService(), id);

							// 수정할 데이터 선택
							whileLoop: while (true) {
								new MemberPrint().print(vo);
								System.out.println();
								System.out
										.println("-------------------------------------------------------------------");
								System.out.println("  1. 비밀번호    2. 회원 이름   3. 회원 생년월일   4. 회원 연락처   5. 회원 주소 ");
								System.out.println("  6. 회원 사진   9. 수정 취소   0. 수정 완료");
								System.out
										.println("-------------------------------------------------------------------");
								String item = In.getStr(" ▷ 수정 항목 선택");

								switch (item) {
								case "1": {
									vo.setPw(In.getStr(" ▷ 비밀번호 변경"));
									break;
								}
								case "2": {
									vo.setName(In.getStr(" ▷ 회원 이름 변경"));
									break;
								}
								case "3": {
									vo.setBirth(In.getStr(" ▷ 생년월일 번경"));
									break;
								}
								case "4": {
									vo.setTel(In.getStr(" ▷ 회원 연락처 변경"));
									break;
								}
								case "5": {
									vo.setAddress(In.getStr(" ▷ 회원 주소 변경"));
									break;
								}
								case "6": {
									vo.setPhoto(In.getStr(" ▷ 회원 사진 변경"));
									break;
								}
								case "9": {
									System.out.println("");
									System.out.println("*** 수정이 취소 되었습니다. ***");
									break whileLoop;
								}
								case "0": {
									vo.setPw(In.getStr(" ▷ 변경하려면 기존 비밀번호를 입력하세요."));
									Execute.execute(new MemberUserUpdateService(), vo);
									System.out.println("*** 회원 정보를 수정 완료했습니다. ***");
									break whileLoop;
								}

								default:
									System.out.println();
									System.out.println("***********************************");
									System.out.println("**** 잘못된 항목 번호를 선택하셨습니다. ****");
									System.out.println(" [1 ~ 6, 9, 0] 번호를 선택하여야 합니다. ");
									System.out.println("***********************************");
									break;
								} // end of update switch
							} // end of update while
							break;

						// 로그아웃 - 비회원 메뉴로 진입
						case "3":
							System.out.println(" ▶ 3. 로그아웃");

							if (Main.login != null)
								Main.login = null;
							System.out.println(" ▶ 정상적으로 로그아웃 되었습니다.");
							break;

						// 이전 메뉴 진입
						case "0":
							System.out.println(" ▶ 이전 메뉴로 돌아갑니다.");
							return;
						default:
							System.out.println("@@@ 잘못된 메뉴를 입력하셨습니다. [1 ~ 3, 0] 중에 입력하세요. @@@");
							break;
						} // end of menu switch

						break;
					default:
						System.out.println("@@@ 오류2 : 잘못된 접근입니다. 메인메뉴로 돌아갑니다. @@@");
						break;
					} // end of 쇼핑몰 메뉴

				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("@@@ 오류 발생 : 정보가 일치하지 않습니다.");
				} // end of grade switch
			} // end of login if
		} // end of login while

	} // end of execute()

} // end of class
