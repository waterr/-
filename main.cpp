#include <iostream>
#include <stdio.h>
#include <vector>
#include <time.h>
#include <stdlib.h>

#define M 2    //运算符个数(操作数个数即为M+1)

#define MP 4        //四则运算
#define MV 10
//÷
using namespace std;

char op[M];     //运算符
int value[M+1];     //操作数
vector<char> s_op;  //符号栈
vector<int> s_value;        //数值栈
int N = 30;      //出题数量
int flag = 1;   //是否输出答案(1表示输出，0表示不输出)
int fsave = 1;  //是否保存试题(1表示保存，0表示不保存)
int final_result; //计算结果


int level(char op)      //分配运算符优先级别
{
    if(op == '*'||op=='/') return 2;
    else return 1;
}

int cal(char op, int value1, int value2)         //对两个操作数进行计算
{
    int result;
    switch(op)
    {
    case '+':
        result = value1+value2;
        break;
    case '-':
        result = value1-value2;
        break;
    case '*':
        result = value1*value2;
        break;
    case '/':
        result = value1/value2;
        break;

    }

    return result;
}


int calculate()         //对整个表达式进行运算
{
    s_op.clear();
    s_value.clear();
    s_op.push_back(op[0]);
    s_value.push_back(value[0]);
    int idx_op = 1;
    int idx_var = 2;

    for(int i=1; i<=M; i++)
    {
        s_value.push_back(value[i]);
        int l1 = level(s_op.at(s_op.size()-1));
        int l2 = level(op[i]);
        if(l1 < l2)
        {
            s_op.push_back(op[i]);
        }

        else
        {
            while(l2 <= l1)
            {
                char t_op = s_op.at(s_op.size()-1);
                s_op.pop_back();
                int v1 =  s_value.at(s_value.size()-1);
                s_value.pop_back();
                int v2 = s_value.at(s_value.size()-1);
                s_value.pop_back();
                int result = cal(t_op, v2, v1);
                s_value.push_back(result);

                if(s_op.empty())
                {
                    break;
                }
                l1 = level(s_op.at(s_op.size()-1));
            }
            s_op.push_back(op[i]);

        }
    }
    int result = s_value.at(0);
    return result;      //返回运算结果
}

void check()            //对试题进行检查确保能到有效计算结果
{
    int divide = -1;
    for(int i=0; i<M; i++)
    {

        if(op[i] == '/')            //当运算符为'/'
        {
            if(value[i+1] == 0)         //处理除数为0的情况
            {
                int t = value[i+1];
                while(t == 0) t = rand()%MP;
                value[i+1] = t;
            }
            if(value[i]%value[i+1]!=0)          //处理不能整除的情况
            {
                if(divide == -1)
                {
                    value[i] = value[i+1]*(rand()%MV + 1);
                    divide = value[i]/value[i+1];
                }
                else
                {
                    if(divide == 1) value[i+1] = 1;
                    else
                    {
                        for(int j=divide-1; j>0; j--)
                        {
                            if(divide%j==0)
                            {

                                value[i+1] = j;
                                divide /= j;
                                break;
                            }
                        }
                    }
                }

            }
            else divide = value[i]/value[i+1];
        }
        else
        {
            divide = -1;
        }
    }
}


char gene_op(int t)         //生成运算符
{
    char op;
    switch(t)
    {
    case 0:
        op = '+';
        break;
    case 1:
        op = '-';
        break;
    case 2:
        op = '*';
        break;
    case 3:
        op = '/';
        break;
    }

    return op;
}

void save()         //保存试题到文本文档
{
    FILE *p = fopen("F://problem.txt","a");
    /*
    for(int i=0; i<M; i++) fprintf(p,"%2d %c ",value[i],op[i]);
    fprintf(p,"%2d = ",value[M]);
    */
    for(int i=0; i<M; i++)        
    {
        fprintf(p,"%2d ",value[i]);
        if(op[i]=='*') fprintf(p,"×");
        else if(op[i]=='/') fprintf(p,"÷");
        else fprintf(p,"%c",op[i]);
    }
    fprintf(p,"%2d = ",value[M]);
    if(flag == 1) fprintf(p," %2d",final_result);
    fprintf(p,"\n");
    fclose(p);

}


void print()
{
    for(int i=0; i<M; i++)        //打印试题
    {
        printf("%2d ",value[i]);
        if(op[i]=='*') printf("×");
        else if(op[i]=='/') printf("÷");
        else printf("%c",op[i]);
    }
    printf("%2d = ",value[M]);


   // final_result = calculate();


    if(flag == 1)                   //是否打印表达式答案
    {
        final_result = calculate();
        printf("%d",final_result);
    }
    printf("\n");
    if(fsave == 1) save();          //是否保存表达式到文本文档
}

void gene_prob()            //生成单个试题
{
    for(int i=0; i<M; i++)      //随机生成运算符和操作数
    {
        int t_op = rand()%MP;
        op[i] = gene_op(t_op);
        value[i] = rand()%MV;
    }
    value[M] = rand()%MV;
    check();                    //对生成的表达式做检查确保能得到有效结果
    /*
    for(int i=0; i<M; i++)        //打印试题
        printf("%2d %c ",value[i],op[i]);
    printf("%2d =  ",value[M]);
      */
      final_result = calculate();
      if(final_result>100||final_result<0) gene_prob();
      else print();

}



void gene_probs()            //生成全部试题
{
    for(int i=0; i<N; i++)
    {
        gene_prob();
    }
}

int main()
{

    srand((unsigned)time(NULL));
    gene_probs();       //生成试题

    return 0;


}
