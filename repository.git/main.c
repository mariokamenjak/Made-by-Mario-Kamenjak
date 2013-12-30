#include<stdio.h>
#include<locale.h>
#include"termihead.h"
int main()
{	
	char op;
	float a,b;	
	int i=1;
	do
	{
	printf("___________________\n");
	printf("| TERMICALC v0.275724577542475427425 |\n");
	printf("-------------------\n");
	printf("\n");
	printf("CHOOSE YOUR MATHEMATICAL OPERATION\n");
	printf("\n");
	printf("[*] MULTIPLY \n");
	printf("[/] DIVIDE \n");
	printf("[+] ADD \n");
	printf("[-] SUBSTRACT \n");
	printf("[M] MODULO \n");
	printf("[A] AVERAGE out of 2 \n");
	printf("[O] CIRCLE\n");
	printf("EXIT [E]\n\n");	
	printf("CREDITS [C]\n\n");
	printf("OPERATION: ");
	scanf(" %c", &op);	
	
	switch(op)
		{
			case 'e':
			{
				return 0;
			}
			case 'E':
			{
				return 0;
			}
			case '*':
			{		
				mul();//those are from "termihead.h"
				break;
			}
			case '/':
			{
				div();
				break;
			}
			case '+':
			{
				sum();
				break;
			}
			case '-':
			{
				sub();
				break;
			}		
			case 'M':
			{
				mod();
				break;
			}
			case 'm':
			{
				mod();
				break;
			}
			case 'A':
			{
				avg();
				break;
			}
			case 'a':
			{
				avg();
				break;
			}
			case 'O':
			{
				cir();
				break;
			}
			case 'o':
			{
				cir();
				break;
			}
			case 'c':
			{
				setlocale(LC_ALL, "croatian" );
				cred();
				break;
			}
			case 'C':
			{
				setlocale(LC_ALL, "croatian" );
				cred();
				break;
			}
			default:
			{
				printf("Wrong input!");
				break;
			}
		}
	printf("\n\n");
	}while(i=1);
	return 0;
}