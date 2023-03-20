package application;

import java.sql.SQLException;
import java.util.Scanner;


import model.Dao.DaoFactory;
import model.Dao.FeedbackDao;
import model.Dao.UserDao;
import model.entites.*;


public class Programa {
	
	public static void main(String[] args) throws SQLException {
		
		Scanner input = new Scanner (System.in);
		
		UserDao userDao = DaoFactory.createUserDao();
		FeedbackDao feedbackDao = DaoFactory.createFeedbackDao();
		
		String closeProgram = "N";

		
		// comando de repetição para prender o usuario no programa ate que ele deseje encerarr
		while (closeProgram == "N") {
			
			//menun de acesso
			int opcaoMenuPrincipal = 0;
			System.out.println("-----------Opcções-----------");
			System.out.println("1-Cadastrar\n2-Login\n3-Sair");
			
			opcaoMenuPrincipal = input.nextInt();
			input.nextLine();
			
			//cadastro de um usuario
			if (opcaoMenuPrincipal == 1) {
				System.out.println("\n1- estudante\n2-Funcionario");
				int opcaoMenuCadastro = input.nextInt();
				input.nextLine();
				
				if (opcaoMenuCadastro == 1) {
					System.out.println("\nNome: ");
					String name = input.nextLine();
					
					System.out.println("Senha: ");
					String password = input.nextLine();
					
					System.out.println("Id:  ");
					String id = input.nextLine();
					
					Student student = new Student(name, password,id);
					userDao.insert(student);
				}
				else {
					System.out.println("\nNome: ");
					String name = input.nextLine();
					
					System.out.println("Senha: ");
					String password = input.nextLine();
					
					System.out.println("Id:  ");
					String id = input.nextLine();
					
					Employee employee = new Employee(name, password,id);
					userDao.insert(employee);
				}

		
			// opcao de login
			} else if (opcaoMenuPrincipal == 2) {

				System.out.println("Id: ");
				String Id = input.nextLine();

				System.out.println("Senha: ");
				String password = input.nextLine();
				//verificção de acesso
				if (userDao.checkLogin(Id, password)) {
					
					if (!userDao.checkAccessType(Id)) {
			
						String sairMenuAluno = "N";
						
						while (sairMenuAluno == "N") {
							//menu principal do aluno
							System.out.println("-------Menu aluno-------");
							System.out.println("\n1- Adicionar feedback\n2- Visualizar todos os feedbacks" + "\n3-Sair");

							int opcao3 = input.nextInt();
							input.nextLine();

							switch (opcao3) {
							//opcao de adicionar feedback
							
							case 1: {

								System.out.println("\nInforme o tipo de feedback: ");
								System.out.println("\n1- Reclamação\n2-Elogio\n3-Sugestão");
								
								int opcao4 = input.nextInt();
								input.nextLine();

								switch (opcao4) {
									
									case 1: 
										System.out.println("\nInforme o feedback: ");
										String description = input.nextLine();
										
										Feedback fd = new Feedback( description,"reclamação");
										feedbackDao.insert(fd, Id);
										break;
									
									case 2:
										System.out.println("\nInforme o feedback: ");
										String description1 = input.nextLine();
										
										Feedback fd1= new Feedback( description1,"elogio");
										feedbackDao.insert(fd1, Id);
										break;
	
									case 3:
										System.out.println("\nInforme o feedback: ");
										String description2 = input.nextLine();
										
										Feedback fd2= new Feedback( description2,"sugestão");
										feedbackDao.insert(fd2, Id);
										break;
	
									default:
										System.out.println("\nOpcao invalida.");
										break;
								}
								break;
							}
							//opcao de vizuailizar todos os feedbacks 
							case 2:

								System.out.println(feedbackDao.finBYStudent(Id));
								break;

							case 3:
								sairMenuAluno = "S";
								break;

							default:
								System.out.println("\nOpcao invalida.");
								break;
							}

						}
					} 
					else {
						
						//menu do funcionario
						String sairMenuFuncionario = "N";
						
						while (sairMenuFuncionario == "N") {
							System.out.println("\n1-Vizualizar Feedback\n2-Remover feedbacks\n3-sair");
							int opcao4 = input.nextInt();
							input.nextLine();
							
							switch (opcao4) {
								
								case 1:	
									//opcao de visualizar feedbacks por aluno
									System.out.println(feedbackDao.findAll());
									System.out.println("\nInforme a id do aluno: ");
									String idUser = input.nextLine();
									System.out.println(feedbackDao.finBYStudent(idUser));
									break;
									
								case 2:
									//opcao de remover feedbacks
									System.out.println("1- Todos todos os feedbacks de um aluno\n2- Deleter feedback especifico ");
									int opcaoDeletarFeedback = input.nextInt();
									input.nextLine();
									
									if(opcaoDeletarFeedback == 1) {
										System.out.println(userDao.findAll());
										System.out.println("\nInforme a id do aluno: ");
										String idUser1 = input.nextLine();
										
										feedbackDao.deleteAllFeedbacks(idUser1);
										
									}
									else {
										System.out.println(feedbackDao.findAll());
										
										System.out.println("Numero do feedbacks: ");
										int numberFeedback = input.nextInt();
										input.nextLine();
										
										feedbackDao.delete(numberFeedback);
										
									}
									break;
									
								case 3:
									
									sairMenuFuncionario = "S";
									break;
									
								default:
									
									System.out.println("\nOpcao invalida");
									break;
							}
						}
					}
				}

			}

			else {
				closeProgram = "s";
			}
		}
		input.close();
	
	}	//fim do metodo main

}// fim da class