package Aplicação;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.entidades.*;
import model.execption.DomainExecption;

public class Programa {
	
	public static void main(String[] args) throws SQLException {
		
		
		
		
		Adm adm = new Adm();
		
		Prints print = new Prints ();
		
		Scanner input = new Scanner (System.in);
		
		DataBaseConnection conn = new DataBaseConnection();
		
		Connection Connec = conn.getConn();
		
		Databasefunctions DB = new Databasefunctions(Connec);
		
		String closeProgram = "N";
		
		
		
		
		
		
		
		while (closeProgram != "S") {
			
			print.login();
			int primaryAcess = 0;

			try {
				
				System.out.println("\nInforme o tipo de acesso: ");
				primaryAcess = input.nextInt();
			} 
			
			catch (InputMismatchException e) {
				
				System.out.println("\nDado de entrada inválido.");
			}
			
			

			input.nextLine();
			
			
			if (primaryAcess == 1) {
				
				int acessUser = 0;
				print.acess();
				
				try {
					
					System.out.println("\nInforme o tipo de acesso: ");
					acessUser = input.nextInt();
					
				} 
				
				catch (InputMismatchException e) {
					
					System.out.println("\nDado de entrada inválido.");
				}
				
				
				input.nextLine();		
				if (acessUser == 1) {
					
					
					String idUser;
					String passwordUser1;
					String nameUser;
					
					
					try {
						
					
						System.out.println("\nCrie um id:");
						idUser = input.nextLine();
						
						
						
						
						
						System.out.println("\nCrie um senha: ");
						passwordUser1 = input.nextLine();
						
						
						
						
						System.out.printf("\nInforme seu nome: ");
						nameUser = input.nextLine();
						
						
						User User1 = new User (nameUser,passwordUser1,idUser);
						DB.insert(User1);
						
						System.out.println("\nCadastro realizado com sucesso.");
						System.out.println("\nAgora acesse como usuario cadastrado.");
						}
				
					catch (DomainExecption e) {
					
						System.out.println("\nErro ao cadastrar");
						System.out.println(e.getMessage());
						
					}
					
					
				}// fim do segundo if
				
				else if (acessUser == 2) {
					
					String id = null;
					String password;
					boolean check = false;
					
					
					System.out.println("\nDigite seu id:");
					id = input.nextLine();
					
					System.out.println("\nDigite sua senha:");
					password = input.nextLine();
					
					check = DB.checkLogin(id, password);
					
					
					
					
					
						if (check == true) {
							
							String longOut = "N";
							
							
							
							while(longOut == "N") {
								
								
								
								print.usersOptions();
							
								int optionUser = 0;
								
								System.out.println(" ");
								
								System.out.println("Informe a opção que deseja:");
								optionUser= input.nextInt();
								
								input.nextLine();
								
								
								
								
								switch (optionUser) {
								
									
									case 1:
									
										print.feedbacksOptions();
										
										
										int optionFeedback = 0;
										
										System.out.println(" ");
										System.out.println("Informe o tipo de feedback:");
										optionFeedback = input.nextInt();
										input.nextLine();
										
										
										switch(optionFeedback) {
									 
											case 1:
											
												String feedbackR;
											
												System.out.println(" ");
												System.out.println("Digite sua reclamção:");
												feedbackR = input.nextLine();
												
												
												
												Reclamacao obj = new Reclamacao (feedbackR);
												DB.insertFeedbacks(id, obj);
												
												
												break;
												
											
											
											case 2:
												
												String feedbackE;
											
												System.out.println(" ");
												System.out.println("Digite seu elogio:");
												feedbackE = input.nextLine();
												
												
												
												Elogio obj1 = new Elogio(feedbackE);											DB.insertFeedbacks(id, obj1);
												
												
												break;
											
												
												
											case 3:
												
												String feedbackS;
											
												System.out.println(" ");
												System.out.println("Digite sua sugestão:");
												feedbackS = input.nextLine();
												
												
												
												Sugestao obj2 = new Sugestao (feedbackS);
												DB.insertFeedbacks(id, obj2);
												
												
												break;
												
											default:
												
												System.out.println(" ");
												System.out.println("Opçõa inválida");
												break;
												
										}//fim do segundo switch
									
									
									
									
									
									case 2:
										
										DB.finBYId(id);
										
										break;
										
									
									
									
									case 3:
										
										int numberFeedback;
										
										DB.finBYId(id);
										
										System.out.println(" ");
										
										
										System.out.println("Informe o numero do feedback: ");
										numberFeedback = input.nextInt();
										input.nextLine();
										DB.deleteFeedback(numberFeedback);
										
										break;
									
									
									
									case 4:
										
										
										String newPassword;
										
										System.out.println(" ");
										System.out.println("Digite sua nova senha:");
										
										newPassword = input.next();
										
										DB.updateUserPassword(id, newPassword);
										
										break;
										
									
									case 5:
										
										DB.deleteAllFeedbacks(id);
										
										DB.deleteUser(id);
										
										longOut = "S";
										
										break;
									
									
									case 6:
										
										longOut = "S";
										
										break;
									
									default:
										
										System.out.println(" ");
										System.out.println("Opção invalida");
										
										break;
							
									
								}// fim do terceiro switch
								
								
							
							
							}//fim do while
						
						
						
						
						}// fim do if
							
						
						
						else {
							
							System.out.println(" ");
							System.out.println("Usuario ou senha incorretos.");
									
						
						
						}// fim do else
					
					
				
				
				
				} // fim do segundo if
			
			
			
			}// fim do primeiro if
			
			
			
			else if(primaryAcess == 2) {
				
				
				String passwordAdm;
				
				
				System.out.println(" ");
				System.out.println("Digite a senha de acesso a area de administração do sistema: ");
				
				passwordAdm = input.next();
				
				
				boolean checkAdm;
				checkAdm = adm.checkPassword(passwordAdm);
				
				
				if (checkAdm == true) {
					String longOutAdm;
					
					longOutAdm = "N";
					
					while (longOutAdm == "N") {
						print.admOptions();
					
						int optionAdm;
						
						
						System.out.println(" ");
						System.out.println("Digite o opção que deseja?: ");
					
						optionAdm = input.nextInt();
						input.nextLine();
						
						
						switch (optionAdm) {
						
							case 1:
							
								DB.findAll();
							
							
								String user;
								System.out.println(" ");
								System.out.println("Informe qual usuário você deseja vizualizar os feedbacks: ");
								user = input.nextLine();
							
								DB.finBYId(user);
								
								break;
							
							
							case 2:
								
								DB.findAll();
								
								
								System.out.println(" ");
								System.out.println("Informe qual usuário você deseja vizualizar os feedbacks: ");
								user = input.next();
								
								DB.finBYId(user);
								
								
								int numberFeedbackDelete;
								
								
								System.out.println(" ");
								System.out.println("Digite o numero do feedback a ser deletado");
								numberFeedbackDelete = input.nextInt();
								
								
								DB.deleteFeedback(numberFeedbackDelete);
								
								break;
							
							case 3:
								
								DB.findAll();
								
								System.out.println(" ");
								System.out.println("Informe qual usuário você deseja vizualizar os feedbacks: ");
								user = input.next();
								
								DB.deleteAllFeedbacks(user);
								
								DB.deleteUser(user);
								
								break;
							
							case 4:
								
								longOutAdm = "S";
								
								break;
							case 5:
								closeProgram = "S";
								longOutAdm = "S";
								break;
							
							default:
								
								
								System.out.println(" ");
								System.out.println("Opção invalida");
								break;
						}
					}
					
				}//if de verificação  senha do adm
				
				else {
					
					System.out.println(" ");
					System.out.println("Senha incorreta");
				}
				
				
					
			
			}
		
		
		}  // fim do while
		
		
		
		input.close();
	
	
	}	//fim do metodo main






}// fim da class