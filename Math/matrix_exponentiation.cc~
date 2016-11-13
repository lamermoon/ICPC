#include<iostream>

using namespace std;

const int nos = 105;

void mult(int a[][nos], int b[][nos], int N)
{
    int res[nos][nos] = {0};
    for(int i = 0; i < N; i++) {
	for(int j = 0; j < N; j++) {
	    for(int k = 0; k < N; k++) {
		res[i][j] = (res[i][j] + a[i][k]*b[k][j]) % 10000;
	    }
	}
    }
    for(int i = 0; i < N; i++) {
	for(int j = 0; j < N; j++) {
	    a[i][j] = res[i][j];
	}
    }
}

int main()
{
    int N, L;    
    while(cin >> N >> L) {
	int S,T;
	cin >> S >> T;
	int g[nos][nos] = {0};
	int s[nos][nos] = {0};
	s[S-1][S-1] = 1;
	for(int i = 0; i < N; i++) {
	    int way;
	    for(int j = 0; j < 4; j++) {
		cin >> way;
		g[i][way-1]++;
	    }
	}
	//calculating s * g^L
	//start with g^L by succ squaring
	int res[nos][nos] = {0};
	for(int i = 0; i < N; i++) {
	    for(int j = 0; j < N; j++) {
		if(i == j) res[i][j] = 1;
	    }
	}
	for(int i = 0; (1 << i) <= L; i++) {
	    if(((1 << i) & L) == (1 << i)) {
		mult(res, g, N);
	    }
	    mult(g, g, N);
	}
	mult(s, res, N);
	cout << s[S-1][T-1] << endl;
    }
}
