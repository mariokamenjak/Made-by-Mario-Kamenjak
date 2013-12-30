#ifndef termihead_h
#define termihead_h
#include<stdio.h>
#include<locale.h>
#define pi 3.14
#include <math.h>
void sum () //+
{
	float f1,f2;
	printf("Input 2 numbers(divided by a space): ");
	scanf("%f %f", &f1, &f2);	
	float c = f1 + f2;
	printf ("Result: %f\n", c);
}
void mul () //*
{
	float f1,f2;
	printf("Input 2 numbers(divided by a space): ");
	scanf("%f %f", &f1, &f2);	
	float c = f1 * f2;
	printf ("Result: %f\n", c);
}
int div()
{
	float f1,f2;
	printf("Input 2 numbers(divided by a space): ");
	scanf("%f %f", &f1, &f2);
	if(f2==0)
	{
		printf("Error\n");
		return 1;
	}
	else
	{
		printf("Result:%f / %f = %f\n", f1, f2, f1/f2);
	}
	return 1;
}
void sub () //-
{
	float f1,f2;
	printf("Input 2 numbers(divided by a space): ");
	scanf("%f %f", &f1, &f2);	
	float c = f1 - f2;
	printf ("Result: %f\n", c);
}
void mod()
{
	float f1,f2;
	printf("Input 2 numbers(divided by a space): ");
	scanf("%f %f", &f1, &f2);
	printf("Result: %f MODULO %f = %d\n", f1, f2, ((int)f1)%((int)f2));
}
void avg()
{
	float f1,f2;
	printf("Input 2 numbers(divided by a space): ");
	scanf("%f %f", &f1, &f2);
	printf("Result: (%f + %f)/2 = %f\n", f1, f2, (f1+f2)/2);
}
void cir()/*Thank you RaZ0R9111*/
{
	float scope,radius,surface,s;
	int i=1,a,b,c;
	printf("Input radius\n");
	scanf("%f", &radius);
	scope=2*radius*pi;
	printf("Scope= %.2f\n",scope);
	surface=radius*radius*pi;
	printf("Surface=%.2f\n",surface);
}
void cred()
{
	setlocale(LC_ALL, "croatian" );
	printf("**\n\tAuthor:\n\tMario Kamenjak a.k.a.\"Bluedragon\"");
	printf("**\n\tco developer:\n\tLuka Zvonar aka \"RaZ0R9111\"");
	printf("**\n\tSpecial thanks to:\n\tKristijan Župan for teaching me how C functions work.");
	printf("**\n\tSpecial thanks to:\n\tBarbara Komočar for teaching me how C headers work.");
	printf("**\n\tSpecial thanks to:\n\tThe staff and the students of the polytechnic of Zagreb for well everything.");
	printf("**\n\tSpecial thanks to:\n\tHrvoje Kovačićek,Ivan Knežević(a.k.a.\"kez\"),Andrija Janček,Nikola Klemeš\t,Petra Laškarin and Jurica Petreković.");
	printf("**\n\tSpecial thanks to:\n\tAlbert Einstein for the theory of relativity.");
	printf("**\n\tSpecial thanks to:\n\tLinus Torvlads for creating a great operating sistem.");
	printf("**\n\tSpecial thanks to:\n\tRichard Stallman for the GNU GPL licence.");
	printf("\n\nThis program is licensed under the GNU GPL v2 licence!");
}
#endif
