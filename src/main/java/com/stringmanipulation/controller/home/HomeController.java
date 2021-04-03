package com.stringmanipulation.controller.home;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"/home", "", "/"})
	public ModelAndView dashboard() {
		ModelAndView view = new ModelAndView("home/home");
		return view;
	}
	
	
	@RequestMapping(value = {"/checkmanipulation"})
	@ResponseBody
	public Map<String, String> checkManipulation(@RequestParam Map<String, String> allRequestParams) {
		String result = "";
		Map<String, String> map = new HashMap();
		try {
			String basedon = "word";
			String split = "\\.";
			String based_on = allRequestParams.get("basedon").toString();
			if(based_on!=null && based_on!="") {
				basedon = based_on;
			}
			String actualString = allRequestParams.get("actualString").toString();
			String manipulation = allRequestParams.get("manipulation").toString();
			
			String frequency = allRequestParams.get("frequency").toString();
			String digit = allRequestParams.get("digit").toString();
			String action = allRequestParams.get("action").toString();
			
			String manipulatedString = "";
				if(actualString!=null && actualString!="") {
					if(manipulation!=null && manipulation!="") { 
						if(actualString.contains(".")) {
							System.err.println("here");
						}else if(actualString.contains(",")) {
							split = ",";
						}
						System.err.println("here split is ---->"+split);
						String[] breakStrings = actualString.split(split);
						System.err.println("length is :"+breakStrings.length);
						if(breakStrings.length == 0) {
							result += actualString;
						}else {
							for (String str : breakStrings) {
								manipulatedString += str+"\n";
					            System.out.println(str);
					            switch (manipulation) {
		            			  case "truncate":
		            			    System.out.println("truncate");
		            			    if(frequency!=null && frequency!="") {
						            	//if(frequency.equals("first")) {//whatever the operation done is the firstly done
						            		if(digit!=null && digit!="" && !digit.equals("0")) {
						            			int n = Integer.parseInt(digit);
						            			result += callTruncate(str,n,action,basedon,frequency);
						            		}else {
						            			result += str+"\n";
						            		}
						            	//}else {
						            		
						            //	}
						            }else {
						            	result += str+"\n";
						            }
		            			    
		            			    break;
		            			  case "reverse":
		            			    System.out.println("reverse");
		            			    result += callReverse(str,digit,action,basedon,frequency);
		            			    
		            			    break;
		            			  case "upparcase":
		            			    System.out.println("upparcase");
		            			    result += callUppercase(str,digit,action,basedon,frequency);
		            			    break;
		            			  case "lowercase":
		            			    System.out.println("lowercase");
		            			    result += callLowercase(str,digit,action,basedon,frequency);
		            			    break;
		            			  case "count":
		            			    System.out.println("count");
		            			    result += str+"--length-->"+str.length()+"\n";
		            			    break;
		            			  default:
		            				    System.out.println("Seems Like You Not Choose Any Manipulation");
		            				    result += "Seems Like You Not Choose Any Manipulation";
		            			}
					            
					            
							}
						}
						
					}else {
						result += actualString;
						manipulatedString += actualString;
					}
				}else {
					result += "Oops! Acutal Sentences Is Blank";
				}
				if(frequency!=null && frequency!="") {
					if(action!=null && action!="") {
						
					}else {
						
					}
				}else {
					
				}
				
				map.put("result", result);
				map.put("manipulatedString", "split the Actual Paragraph using --->"+split+"\n"+manipulatedString);
		} catch (Exception e) {
			e.printStackTrace();
			result += "Oops! Something went wrong";
			map.put("result", result);
			map.put("manipulatedString", "split the Actual Paragraph using --->");
		}
		
			
			
		return map;
	}


	private String callUppercase(String str, String digit, String action, String basedon, String frequency) {
		String result = "";
		if(frequency!=null && frequency!="") {
    		if(digit!=null && digit!="" && !digit.equals("0")) {
    			int n = Integer.parseInt(digit);
    			//result += callReverse(str,n,action,basedon,frequency);
    			if(frequency.equals("first")) {
    				if(basedon.equals("character")) {
    					String charcterArray = "";
    					if(action!=null && action!="") {
    						
    						if(action.equals("exclued")) {
    							System.err.println("first "+n+" character is exclued and other will be -> uppercase");
    							if(str.length() > n) {
    								charcterArray += str.substring(0,n);
    								char ch[]=str.toCharArray();  
    								for(int i=n;i<ch.length;i++){  
    									charcterArray+= Character.toUpperCase(ch[i]);  
    					            } 
    			    			}
    			    			
    						}else {
    							System.err.println("first "+n+" character is uppercase and other will be same");
    							if(str.length() > n) {
    								
    								char ch[]=str.toCharArray();  
    								for(int i=0;i<n;i++){  
    									charcterArray+=Character.toUpperCase(ch[i]);  
    					            }
    								charcterArray += str.substring(n,str.length());
    			    			}
    						}
    						
    					}else {
    						System.err.println("first "+n+" character is exclued and other will be -> uppercase");
							if(str.length() > n) {
								charcterArray += str.substring(0,n);
								char ch[]=str.toCharArray();  
								for(int i=n;i<ch.length;i++){  
									charcterArray+= Character.toUpperCase(ch[i]);  
					            }  
			    			}
    					}
    					result += charcterArray+"\n";
    				}else if(basedon.equals("word")) {
    					String[] chunks = str.split("\\s+");
    					String chunksString = "";
    					if(action!=null && action!="") {
    						if(action.equals("exclued")) {
    							if(chunks.length > n) {
    								System.err.println("first "+n+" word is exclued|skip and other will be -> uppercase");
    								for(int i =0;i<n;i++) {
    						            //System.out.println(chunks[i]);
    									chunksString += chunks[i]+" ";
    								}
    						        for(int i =n;i<chunks.length;i++) {
    									//System.out.println(chunks[i]);
    						        	chunksString += chunks[i].toUpperCase()+" ";
    								}
    			    			}
    			    			
    						}else {
    							System.err.println("first "+n+" word is uppercase and other will be ->remain same");
    							if(chunks.length > n) {
    								int newlength = chunks.length-n;
    								for(int i =0;i<n;i++) {
    									chunksString += chunks[i].toUpperCase()+" ";
    								}
    								for(int i =n;i<chunks.length;i++) {
    									chunksString += chunks[i]+" ";
    								}
    			    			}
    							
    						}
    						//result += str+"\n";
    					}else {
    						System.err.println("first "+n+" word is exclued|skip and other will be -> uppercase");
							for(int i =0;i<n;i++) {
					            //System.out.println(chunks[i]);
								chunksString += chunks[i]+" ";
							}
					        for(int i =n;i<chunks.length;i++) {
								//System.out.println(chunks[i]);
					        	chunksString += chunks[i].toUpperCase()+" ";
							}
    						
    					}
    					System.err.println("HERE str is :"+str);
    					result += chunksString+"\n";
    				}
    			}else if(frequency.equals("last")) {
    				if(basedon.equals("character")) {
    					String charcterArray = "";
    					if(action!=null && action!="") {
    						if(action.equals("exclued")) {
    							System.err.println("last "+n+" character is exclued|skip and other will be -> uppercase");
    							if(str.length() > n) {
    								int newlength =  str.length()-n;
    								char ch[]=str.toCharArray();  
    								for(int i=0;i<newlength;i++){  
    									charcterArray+=Character.toUpperCase(ch[i]);  
    					            }
    								charcterArray += str.substring(newlength);
    			    			}
    			    			
    						}else {
    							System.err.println("last "+n+" character is uppercase and other will be same");
    							if(str.length() > n) {
    								charcterArray += str.substring(0,str.length()-n);
    								char ch[]=str.toCharArray();  
    								for(int i=str.length()-n-1;i<ch.length;i++){  
    									charcterArray+=Character.toUpperCase(ch[i]);  
    					            }
    								
    			    			}
    							
    						}
    						
    					}else {
    						System.err.println("last "+n+" character is exclued|skip and other will be -> uppercase");
    						if(str.length() > n) {
								int newlength =  str.length()-n;
								char ch[]=str.toCharArray();  
								for(int i=0;i<newlength;i++){  
									charcterArray+=Character.toUpperCase(ch[i]);  
					            }
								charcterArray += str.substring(newlength);
			    			}
    					}
    					result += charcterArray+"\n";
    				}else if(basedon.equals("word")) {
    					String[] chunks = str.split("\\s+");
    					String chunksString = "";
    					if(action!=null && action!="") {
    						if(action.equals("exclued")) {
    							System.err.println("last "+n+" word is exclued|skip and other will be -> uppercase");
    							if(chunks.length > n) {
    								int newlength = chunks.length-n;
    								for(int i =0;i<newlength;i++) {
    									chunksString += chunks[i].toUpperCase()+" ";
    								}
    								for(int i =newlength;i<chunks.length;i++) {
    									chunksString += chunks[i]+" ";
    								}
    			    			}
    			    			
    						}else {
    							System.err.println("last "+n+" word is inclued and other will be -> remain same");
    							if(chunks.length > n) {
    								for(int i =0;i<n;i++) {
    						            //System.out.println(chunks[i]);
    									chunksString += chunks[i]+" ";
    								}
    						        for(int i =n;i<chunks.length;i++) {
    									//System.out.println(chunks[i]);
    						        	chunksString += chunks[i].toUpperCase()+" ";
    								}
    			    			}
    							
    						}
    						//result += str+"\n";
    					}else {
    						System.err.println("last "+n+" word is exclued|skip and other will be -> uppercase");
    						if(chunks.length > n) {
								int newlength = chunks.length-n;
								for(int i =0;i<newlength;i++) {
									chunksString += chunks[i].toUpperCase()+" ";
								}
								for(int i =newlength;i<chunks.length;i++) {
									chunksString += chunks[i]+" ";
								}
			    			}
    					}
    					System.err.println("HERE str is :"+str);
    					result += chunksString+"\n";
    				}
    			}
    		}else {
    			result += str+"\n";
    		}
        }else {
        	result += str.toUpperCase()+"\n";
        	
        }
		return result;
	}
	
	private String callLowercase(String str, String digit, String action, String basedon, String frequency) {
		String result = "";
		if(frequency!=null && frequency!="") {
    		if(digit!=null && digit!="" && !digit.equals("0")) {
    			int n = Integer.parseInt(digit);
    			//result += callReverse(str,n,action,basedon,frequency);
    			if(frequency.equals("first")) {
    				if(basedon.equals("character")) {
    					String charcterArray = "";
    					if(action!=null && action!="") {
    						
    						if(action.equals("exclued")) {
    							System.err.println("first "+n+" character is exclued and other will be -> Lowercase");
    							if(str.length() > n) {
    								charcterArray += str.substring(0,n);
    								char ch[]=str.toCharArray();  
    								for(int i=n;i<ch.length;i++){  
    									charcterArray+= Character.toLowerCase(ch[i]);  
    					            } 
    			    			}
    			    			
    						}else {
    							System.err.println("first "+n+" character is Lowercase and other will be same");
    							if(str.length() > n) {
    								
    								char ch[]=str.toCharArray();  
    								for(int i=0;i<n;i++){  
    									charcterArray+=Character.toLowerCase(ch[i]);  
    					            }
    								charcterArray += str.substring(n,str.length());
    			    			}
    						}
    						
    					}else {
    						System.err.println("first "+n+" character is exclued and other will be -> Lowercase");
							if(str.length() > n) {
								charcterArray += str.substring(0,n);
								char ch[]=str.toCharArray();  
								for(int i=n;i<ch.length;i++){  
									charcterArray+= Character.toLowerCase(ch[i]);  
					            }  
			    			}
    					}
    					result += charcterArray+"\n";
    				}else if(basedon.equals("word")) {
    					String[] chunks = str.split("\\s+");
    					String chunksString = "";
    					if(action!=null && action!="") {
    						if(action.equals("exclued")) {
    							if(chunks.length > n) {
    								System.err.println("first "+n+" word is exclued|skip and other will be -> Lowercase");
    								for(int i =0;i<n;i++) {
    						            //System.out.println(chunks[i]);
    									chunksString += chunks[i]+" ";
    								}
    						        for(int i =n;i<chunks.length;i++) {
    									//System.out.println(chunks[i]);
    						        	chunksString += chunks[i].toLowerCase()+" ";
    								}
    			    			}
    			    			
    						}else {
    							System.err.println("first "+n+" word is Lowercase and other will be ->remain same");
    							if(chunks.length > n) {
    								int newlength = chunks.length-n;
    								for(int i =0;i<n;i++) {
    									chunksString += chunks[i].toLowerCase()+" ";
    								}
    								for(int i =n;i<chunks.length;i++) {
    									chunksString += chunks[i]+" ";
    								}
    			    			}
    							
    						}
    						//result += str+"\n";
    					}else {
    						System.err.println("first "+n+" word is exclued|skip and other will be -> Lowercase");
							for(int i =0;i<n;i++) {
					            //System.out.println(chunks[i]);
								chunksString += chunks[i]+" ";
							}
					        for(int i =n;i<chunks.length;i++) {
								//System.out.println(chunks[i]);
					        	chunksString += chunks[i].toLowerCase()+" ";
							}
    						
    					}
    					System.err.println("HERE str is :"+str);
    					result += chunksString+"\n";
    				}
    			}else if(frequency.equals("last")) {
    				if(basedon.equals("character")) {
    					String charcterArray = "";
    					if(action!=null && action!="") {
    						if(action.equals("exclued")) {
    							System.err.println("last "+n+" character is exclued|skip and other will be -> Lowercase");
    							if(str.length() > n) {
    								int newlength =  str.length()-n;
    								char ch[]=str.toCharArray();  
    								for(int i=0;i<newlength;i++){  
    									charcterArray+=Character.toLowerCase(ch[i]);  
    					            }
    								charcterArray += str.substring(newlength);
    			    			}
    			    			
    						}else {
    							System.err.println("last "+n+" character is Lowercase and other will be same");
    							if(str.length() > n) {
    								charcterArray += str.substring(0,str.length()-n);
    								char ch[]=str.toCharArray();  
    								for(int i=str.length()-n-1;i<ch.length;i++){  
    									charcterArray+=Character.toLowerCase(ch[i]);  
    					            }
    								
    			    			}
    							
    						}
    						
    					}else {
    						System.err.println("last "+n+" character is exclued|skip and other will be -> Lowercase");
    						if(str.length() > n) {
								int newlength =  str.length()-n;
								char ch[]=str.toCharArray();  
								for(int i=0;i<newlength;i++){  
									charcterArray+=Character.toLowerCase(ch[i]);  
					            }
								charcterArray += str.substring(newlength);
			    			}
    					}
    					result += charcterArray+"\n";
    				}else if(basedon.equals("word")) {
    					String[] chunks = str.split("\\s+");
    					String chunksString = "";
    					if(action!=null && action!="") {
    						if(action.equals("exclued")) {
    							System.err.println("last "+n+" word is exclued|skip and other will be -> Lowercase");
    							if(chunks.length > n) {
    								int newlength = chunks.length-n;
    								for(int i =0;i<newlength;i++) {
    									chunksString += chunks[i].toLowerCase()+" ";
    								}
    								for(int i =newlength;i<chunks.length;i++) {
    									chunksString += chunks[i]+" ";
    								}
    			    			}
    			    			
    						}else {
    							System.err.println("last "+n+" word is inclued and other will be -> remain same");
    							if(chunks.length > n) {
    								for(int i =0;i<n;i++) {
    						            //System.out.println(chunks[i]);
    									chunksString += chunks[i]+" ";
    								}
    						        for(int i =n;i<chunks.length;i++) {
    									//System.out.println(chunks[i]);
    						        	chunksString += chunks[i].toLowerCase()+" ";
    								}
    			    			}
    							
    						}
    						//result += str+"\n";
    					}else {
    						System.err.println("last "+n+" word is exclued|skip and other will be -> Lowercase");
    						if(chunks.length > n) {
								int newlength = chunks.length-n;
								for(int i =0;i<newlength;i++) {
									chunksString += chunks[i].toLowerCase()+" ";
								}
								for(int i =newlength;i<chunks.length;i++) {
									chunksString += chunks[i]+" ";
								}
			    			}
    					}
    					System.err.println("HERE str is :"+str);
    					result += chunksString+"\n";
    				}
    			}
    		}else {
    			result += str+"\n";
    		}
        }else {
        	result += str.toLowerCase()+"\n";
        	
        }
		return result;
	}


	private String callReverse(String str, String digit, String action, String basedon, String frequency) {
		String result = "";
		if(frequency!=null && frequency!="") {
    		if(digit!=null && digit!="" && !digit.equals("0")) {
    			int n = Integer.parseInt(digit);
    			//result += callReverse(str,n,action,basedon,frequency);
    			if(frequency.equals("first")) {
    				if(basedon.equals("character")) {
    					String charcterArray = "";
    					if(action!=null && action!="") {
    						
    						if(action.equals("exclued")) {
    							System.err.println("first "+n+" character is exclued and other will be -> Reverse");
    							if(str.length() > n) {
    								charcterArray += str.substring(0,n);
    								char ch[]=str.toCharArray();  
    								for(int i=ch.length-1;i>=n;i--){  
    									charcterArray+=ch[i];  
    					            } 
    			    			}
    			    			
    						}else {
    							System.err.println("first "+n+" character is reverse and other will be same");
    							if(str.length() > n) {
    								
    								char ch[]=str.toCharArray();  
    								for(int i=n-1;i>=0;i--){  
    									charcterArray+=ch[i];  
    					            }
    								charcterArray += str.substring(n,str.length());
    			    			}
    						}
    						
    					}else {
    						System.err.println("first "+n+" character is exclued and other will be -> Reverse");
							if(str.length() > n) {
								charcterArray += str.substring(0,n);
								char ch[]=str.toCharArray();  
								for(int i=ch.length-1;i>=n;i--){  
									charcterArray+=ch[i];  
					            } 
			    			}
    					}
    					result += charcterArray+"\n";
    				}else if(basedon.equals("word")) {
    					String[] chunks = str.split("\\s+");
    					String chunksString = "";
    					if(action!=null && action!="") {
    						if(action.equals("exclued")) {
    							if(chunks.length > n) {
    								System.err.println("first "+n+" word is exclued|skip and other will be -> Reverse");
    								for(int i =0;i<n;i++) {
    						            //System.out.println(chunks[i]);
    									chunksString += chunks[i]+" ";
    								}
    						        for(int i =chunks.length-1;i>=n;i--) {
    									//System.out.println(chunks[i]);
    						        	chunksString += chunks[i]+" ";
    								}
    			    			}
    			    			
    						}else {
    							System.err.println("first "+n+" word is inclued and other will be ->remain same");
    							if(chunks.length > n) {
    								int newlength = chunks.length-n;
    								for(int i =n-1;i>=0;i--) {
    									chunksString += chunks[i]+" ";
    								}
    								for(int i =n;i<chunks.length;i++) {
    									chunksString += chunks[i]+" ";
    								}
    			    			}
    							
    						}
    						//result += str+"\n";
    					}else {
    						System.err.println("first "+n+" word is exclued|skip and other will be -> Reverse");
							for(int i =0;i<n;i++) {
					            //System.out.println(chunks[i]);
								chunksString += chunks[i]+" ";
							}
					        for(int i =chunks.length-1;i>=n;i--) {
								//System.out.println(chunks[i]);
					        	chunksString += chunks[i]+" ";
							}
    						
    					}
    					System.err.println("HERE str is :"+str);
    					result += chunksString+"\n";
    				}
    			}else if(frequency.equals("last")) {
    				if(basedon.equals("character")) {
    					String charcterArray = "";
    					if(action!=null && action!="") {
    						if(action.equals("exclued")) {
    							System.err.println("last "+n+" character is exclued|skip and other will be -> Reverse");
    							if(str.length() > n) {
    								int newlength =  str.length()-n;
    								char ch[]=str.toCharArray();  
    								for(int i=newlength-1;i>=0;i--){  
    									charcterArray+=ch[i];  
    					            }
    								charcterArray += str.substring(newlength);
    			    			}
    			    			
    						}else {
    							System.err.println("last "+n+" character is reverse and other will be same");
    							if(str.length() > n) {
    								charcterArray += str.substring(0,str.length()-n);
    								char ch[]=str.toCharArray();  
    								for(int i=ch.length-1;i>=str.length()-n;i--){  
    									charcterArray+=ch[i];  
    					            }
    								
    			    			}
    							
    						}
    						
    					}else {
    						System.err.println("last "+n+" character is exclued|skip and other will be -> Reverse");
							if(str.length() > n) {
								int newlength =  str.length()-n;
								char ch[]=str.toCharArray();  
								for(int i=newlength-1;i>=0;i--){  
									charcterArray+=ch[i];  
					            }
								charcterArray += str.substring(newlength);
			    			}
    					}
    					result += charcterArray+"\n";
    				}else if(basedon.equals("word")) {
    					String[] chunks = str.split("\\s+");
    					String chunksString = "";
    					if(action!=null && action!="") {
    						if(action.equals("exclued")) {
    							System.err.println("last "+n+" word is exclued|skip and other will be -> Reverse");
    							if(chunks.length > n) {
    								int newlength = chunks.length-n;
    								for(int i =newlength-1;i>=0;i--) {
    									chunksString += chunks[i]+" ";
    								}
    								for(int i =newlength;i<chunks.length;i++) {
    									chunksString += chunks[i]+" ";
    								}
    			    			}
    			    			
    						}else {
    							System.err.println("last "+n+" word is inclued and other will be -> remain same");
    							if(chunks.length > n) {
    								for(int i =0;i<chunks.length-n;i++) {
    						            //System.out.println(chunks[i]);
    									chunksString += chunks[i]+" ";
    								}
    						        for(int i =chunks.length-1;i>=chunks.length-n;i--) {
    									//System.out.println(chunks[i]);
    						        	chunksString += chunks[i]+" ";
    								}
    			    			}
    							
    						}
    						//result += str+"\n";
    					}else {
    						System.err.println("last "+n+" word is exclued|skip and other will be -> Reverse");
							if(chunks.length > n) {
								int newlength = chunks.length-n;
								for(int i =newlength-1;i>=0;i--) {
									chunksString += chunks[i]+" ";
								}
								for(int i =newlength;i<chunks.length;i++) {
									chunksString += chunks[i]+" ";
								}
			    			}
    					}
    					System.err.println("HERE str is :"+str);
    					result += chunksString+"\n";
    				}
    			}
    		}else {
    			result += str+"\n";
    		}
        }else {
        	System.err.println("HELLO HERE");
        	if(basedon.equals("character")) {
        		StringBuffer sbr = new StringBuffer(str);
        		
        		result += sbr.reverse();
        	}else if(basedon.equals("word")) {
        		String[] chunks = str.split("\\s+");
        		System.err.println("HELLO HERE"+chunks.length);
        		if(chunks.length>1) {
        			String chunksString = "";
    				for(int i =chunks.length-1;i>=0;i--) {
    					chunksString += chunks[i]+" ";
    				}
    				result += chunksString+"\n";
        		}else {
        			StringBuffer sbr = new StringBuffer(str);
            		
            		result += sbr.reverse();
        		}
				
        	}
        	
        }
		return result;
	}


	private String callTruncate(String str, int n, String action,String basedon,String frequency) {
		
		String result= "";
		if(frequency.equals("first")) {
			if(basedon.equals("character")) {
				if(action!=null && action!="") {
					if(action.equals("exclued")) {
						if(str.length() > n) {
		    				//str = str.substring(n,str.length());
							str = str.substring(0,n);
		    			}
		    			
					}else {
						if(str.length() > n) {
		    				//str = str.substring(0,n);
							str = str.substring(n,str.length());
		    			}
					}
					result += str+"\n";
				}else {
					if(str.length() > n) {
						str = str.substring(0,n);
					}
					result += str+"\n";
				}
			}else if(basedon.equals("word")) {
				String[] chunks = str.split("\\s+");
				String chunksString = "";
				if(action!=null && action!="") {
					if(action.equals("exclued")) {
						if(chunks.length > n) {

							for(int i =0;i<n;i++) {
								chunksString += chunks[i]+" ";
							}
		    			}
		    			
					}else {
						if(chunks.length > n) {
							for(int i =n;i<chunks.length;i++) {
								chunksString += chunks[i]+" ";
							}
		    			}
						
					}
					//result += str+"\n";
				}else {
					if(chunks.length > n) {
						for(int i =0;i<n;i++) {
							chunksString += chunks[i]+" ";
						}
	    			}
					
				}
				System.err.println("HERE str is :"+str);
				result += chunksString+"\n";
			}
		}else if(frequency.equals("last")) {
			if(basedon.equals("character")) {
				if(action!=null && action!="") {
					if(action.equals("exclued")) {
						if(str.length() > n) {
		    				str = str.substring(str.length()-n);
		    			}
		    			
					}else {
						if(str.length() > n) {
		    				str = str.substring(0,str.length()-n);
		    			}
					}
					result += str+"\n";
				}else {
					if(str.length() > n) {
						str = str.substring(str.length()-n);
					}
					result += str+"\n";
				}
			}else if(basedon.equals("word")) {
				String[] chunks = str.split("\\s+");
				String chunksString = "";
				if(action!=null && action!="") {
					if(action.equals("exclued")) {
						if(chunks.length > n) {
		    				//str = str.substring(n,str.length());
							for(int i =chunks.length-n;i<chunks.length;i++) {
								chunksString += chunks[i]+" ";
							}
		    			}
		    			
					}else {
						if(chunks.length > n) {
		    				//str = str.substring(n,str.length());
							for(int i =0;i<chunks.length-n;i++) {
								chunksString += chunks[i]+" ";
							}
		    			}
						
					}
					//result += str+"\n";
				}else {
					if(chunks.length > n) {
	    				//str = str.substring(n,str.length());
						for(int i =chunks.length-n;i<chunks.length;i++) {
							chunksString += chunks[i]+" ";
						}
	    			}
					
				}
				System.err.println("HERE str is :"+str);
				result += chunksString+"\n";
			}
		}
		
		
		return result;
		
	}
	

}
