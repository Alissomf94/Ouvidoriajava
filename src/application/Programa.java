package application;

import java.sql.SQLException;

import DB.DataBase;
import model.Dao.DaoFactory;
import model.Dao.FeedbackDao;
import model.Dao.UserDao;
import model.entites.*;
import model.util.InputValidation;


public class Programa {
	
	public static void main(String[] args) throws SQLException {
		
		UserDao userDao = DaoFactory.createUserDao();
		FeedbackDao feedbackDao = DaoFactory.createFeedbackDao();
		
		String closeProgram = "N";

		// comando de repetição para prender o usuario no programa ate que ele deseje encerarr
		while (closeProgram == "N") {
			
			//menun de acesso
			int opcaoMenuPrincipal = 0;
			System.out.println("-----------Opcções-----------");
			System.out.println("1-Cadastrar\n2-Login\n3-Sair");
			
			opcaoMenuPrincipal = InputValidation.validationInt(opcaoMenuPrincipal);
			
			String name = null;
			String password = null;
			String id = null;
			
			//cadastro de um usuario
			if (opcaoMenuPrincipal == 1) {
				System.out.println("\n1- estudante\n2-Funcionario");
				int opcaoMenuCadastro = 0;
				opcaoMenuCadastro = InputValidation.validationInt(opcaoMenuCadastro);
				
				if (opcaoMenuCadastro == 1) {
					System.out.println("\nNome: ");
					name = InputValidation.validationString(name);
					
					System.out.println("Senha: ");
					password = InputValidation.validationString(password);
					
					System.out.println("Id:  ");
					id = InputValidation.validationString(id);
					
					Student student = new Student(name, password,id);
					userDao.insert(student);
				}
				else if (opcaoMenuCadastro == 2 ){
					System.out.println("\nNome: ");
					name = InputValidation.validationString(name);
					
					System.out.println("Senha: ");
					password = InputValidation.validationString(password);
					
					System.out.println("Id:  ");
					id = InputValidation.validationString(id);
					
					Employee employee = new Employee(name, password,id);
					userDao.insert(employee);
				}

		
			// opcao de login
			} else if (opcaoMenuPrincipal == 2) {

				System.out.println("Id: ");
				id = InputValidation.validationString(id);

				System.out.println("Senha: ");
				password = InputValidation.validationString(password);
				//verificção de acesso
				if (userDao.checkLogin(id, password)) {
					
					if (!userDao.checkAccessType(id)) {
			
						String sairMenuAluno = "N";
						
						while (sairMenuAluno == "N") {
							//menu principal do aluno
							System.out.println("-------Menu aluno-------");
							System.out.println("\n1- Adicionar feedback\n2- Visualizar todos os feedbacks" + "\n3-Sair");

							int opcao3 = 0;
							opcao3 = InputValidation.validationInt(opcao3);

							switch (opcao3) {
							//opcao de adicionar feedback
							
							case 1: {
								int opcao4 = 0;
								System.out.println("\nInforme o tipo de feedback: ");
								System.out.println("\n1- Reclamação\n2-Elogio\n3-Sugestão");
								
								opcao4 = InputValidation.validationInt(opcao4);
								Feedback fd = null;
								String description = null;
								
								switch (opcao4) {
								
									case 1: 
										System.out.println("\nInforme o feedback: ");
										description = InputValidation.validationString(description);
										
										fd = new Feedback( description,"reclamação");
										feedbackDao.insert(fd, id);
										break;
									
									case 2:
										System.out.println("\nInforme o feedback: ");
										description = InputValidation.validationString(description);
										
										fd = new Feedback( description,"elogio");
										feedbackDao.insert(fd, id);
										break;
	
									case 3:
										System.out.println("\nInforme o feedback: ");
										description = InputValidation.validationString(description);
										
										fd = new Feedback( description,"sugestão");
										feedbackDao.insert(fd, id);
										break;
	
									default:
										
										break;
								}
								break;
							}
							//opcao de vizuailizar todos os feedbacks 
							case 2:

								System.out.println(feedbackDao.finBYStudent(id,userDao.findById(id)));
								break;

							case 3:
								sairMenuAluno = "S";
								break;

							default:
								
								break;
							}

						}
					} 
					else {
						
						//menu do funcionario
						String sairMenuFuncionario = "N";
						
						while (sairMenuFuncionario == "N") {
							System.out.println("\n1-Vizualizar Feedback\n2-Remover feedbacks\n3-sair");
							int opcao4 = 0;
							opcao4 = InputValidation.validationInt(opcao4);
							
							switch (opcao4) {
								
								case 1:	
									//opcao de visualizar feedbacks por aluno
									System.out.println(userDao.findAll());
									
									System.out.println("\nInforme a id do aluno: ");
									id = InputValidation.validationString(id);
									
									System.out.println(feedbackDao.finBYStudent(id,userDao.findById(id)));
									break;
									
								case 2:
									//opcao de remover feedbacks
									
									System.out.println("1- Todos todos os feedbacks de um aluno\n2- Deleter feedback especifico ");
									
									int opcaoDeletarFeedback = 0;
									opcaoDeletarFeedback = InputValidation.validationInt(opcaoDeletarFeedback);
									
									if(opcaoDeletarFeedback == 1) 
									{
										System.out.println(userDao.findAll());
										
										System.out.println("\nInforme a id do aluno: ");
										id = InputValidation.validationString(id);
										
										feedbackDao.deleteAllFeedbacks(userDao.findById(id));
										
									}
									else {
										String listFeedbacks = feedbackDao.findAll();
										if (!listFeedbacks.equals("") ) {
											System.out.println(listFeedbacks);
											System.out.println("Numero do feedbacks: ");
											int numberFeedback = 0;
											numberFeedback = InputValidation.validationInt(numberFeedback);
										
											feedbackDao.delete(numberFeedback);
										}
									}
									break;
									
								case 3:
									
									sairMenuFuncionario = "S";
									break;
									
								default:
									
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
		
		InputValidation.closeScanner();
		DataBase.closeConnection();
	}	//fim do metodo main

}// fim da class