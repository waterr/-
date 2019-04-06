#include <iostream>
#include <stdio.h>
#include <vector>
#include <time.h>
#include <stdlib.h>

#define M 2    //���������
#define MP 4        //��������
#define MV 10

using namespace std;

char op[M];     //�����
int value[M+1];     //������
vector<char> s_op;  //����ջ
vector<int> s_value;        //��ֵջ
int N = 30;      //��������
int flag = 0;   //�Ƿ������(1��ʾ�����0��ʾ�����)
int fsave = 1;  //�Ƿ񱣴�����(1��ʾ���棬0��ʾ������)
int final_result; //������


int level(char op)      //������������ȼ���
{
    if(op == '*'||op=='/') return 2;
    else return 1;
}

int cal(char op, int value1, int value2)         //���������������м���
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


int calculate()         //���������ʽ��������
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
    return result;      //����������
}

void check()            //��������м��ȷ���ܵ���Ч������
{
    int divide = -1;
    for(int i=0; i<M; i++)
    {

        if(op[i] == '/')            //�������Ϊ'/'
        {
            if(value[i+1] == 0)         //�������Ϊ0�����
            {
                int t = value[i+1];
                while(t == 0) t = rand()%MP;
                value[i+1] = t;
            }
            if(value[i]%value[i+1]!=0)          //���������������
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


char gene_op(int t)         //���������
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

void save() {       //�������⵽�ı��ĵ�
    FILE *p = fopen("F://problem.txt","a");
    for(int i=0; i<M; i++) fprintf(p,"%2d %c ",value[i],op[i]);
    fprintf(p,"%2d = ",value[M]);
    if(flag == 1) fprintf(p," %2d",final_result);
    fprintf(p,"\n");
    fclose(p);

}

void gene_prob()            //���ɵ�������
{
    for(int i=0; i<M; i++)      //�������������Ͳ�����
    {
        int t_op = rand()%MP;
        op[i] = gene_op(t_op);
        value[i] = rand()%MV;
    }
    value[M] = rand()%MV;
    check();                    //�����ɵı��ʽ�����ȷ���ܵõ���Ч���
    for(int i=0; i<M; i++)        //��ӡ����
        printf("%2d %c ",value[i],op[i]);
    printf("%2d =  ",value[M]);
    if(flag == 1)                   //�Ƿ��ӡ���ʽ��
    {
        final_result = calculate();
        printf("%d",final_result);
    }
    printf("\n");
    if(fsave == 1) save();          //�Ƿ񱣴���ʽ���ı��ĵ�
}

void gene_probs()            //����ȫ������
{
    for(int i=0; i<N; i++)
    {
        gene_prob();
    }
}

int main()
{

    srand((unsigned)time(NULL));
    gene_probs();       //��������

    return 0;


}
