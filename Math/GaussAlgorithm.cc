/* GEV
 */
#include <iostream>
//START
#include <vector>
#include <algorithm>
#include <string>
#include <cmath>
#include <cstdio>
#include <cstring>

using namespace std;

template<int M> class vec
{
public:
	double co[M];
	
	vec<M>() { memset(co, 0, M * sizeof(double)); }
	
	double* operator[](int i) { return &co[i]; }
	
	vec<M> operator+(vec<M> v)
	{
		vec<M> r;
		for(int i = 0; i < M; ++i)
			*r[i] = co[i] + *v[i];
		return r;
	}
	
	vec<M> operator-(vec<M> v)
	{
		vec<M> r;
		for(int i = 0; i < M; ++i)
			*r[i] = co[i] - *v[i];
		return r;
	}
	
	vec<M> operator-()
	{
		vec<M> r;
		for(int i = 0; i < M; ++i)
			*r[i] = -co[i];
		return r;
	}
	
	vec<M> operator*(double s)
	{
		vec<M> r;
		for(int i = 0; i < M; ++i)
			*r[i] = s * co[i];
		return r;
	}
	
	// Kreuzprodukt
	vec<3> cross(vec<3> v)
	{
		vec<3> r;
		*r[0] = co[1] * *v[2] - co[2] * *v[1];
		*r[1] = co[2] * *v[0] - co[0] * *v[2];
		*r[2] = co[0] * *v[1] - co[1] * *v[0];
		return r;
	}
};

template<int M, int N> class mat
{
public:
	double el[M][N];
	
	mat<M, N>() { memset(el, 0, M * N * sizeof(double));  }
	
	double* operator[](int i) { return el[i]; } // Gib Zeile i
	
	// MxN-Matrix mal Nx1-Vektor = Mx1-Vektor
	vec<M> operator*(vec<N> v)
	{
		vec<M> r;
		for(int i = 0; i < M; ++i)
			for(int j = 0; j < N; ++j)
				*r[i] += el[i][j] * *v[j]; // r ist durch Konstruktur genullt
		return r;
	}
	
	// Gauß-Jordan-Algorithmus-Aufruf für MxN-Matrix und Mx1-Vektor
	// Setzt voraus, dass Lösung existiert! => Nur bei MxM-Matrizen sinnvoll
	vec<M> solveLGS(vec<M> in)
	{
		mat<M, N> inp;
		for(int i = 0; i < M; ++i)
			inp[i][0] = *in[i];
		mat<M, N> re = gaussJordan(inp);
		vec<M> r;
		for(int i = 0; i < M; ++i)
			*r[i] = re[i][0];
		return r;
	}
	
	// Gauß-Jordan-Algorithmus für zwei MxN-Matrizen
	// Setzt voraus, dass Lösung existiert! => Nur bei MxM-Matrizen sinnvoll
	mat<M, N> gaussJordan(mat<M, N> in)
	{
		// Erweiterte Matrix erstellen
		double ext[M][N << 1];
		for(int i = 0; i < M; ++i)
		{
			memcpy(ext[i], el[i], N * sizeof(double));
			memcpy(ext[i] + N, in[i], N * sizeof(double));
		}
		
		// Für jede Restmatrix Schritte durchführen
		for(int LC = 0; LC < M && LC < N; ++LC)
		{
			// Finde Spalte mit Zelle != 0
			int c = LC;
			int l = LC;
			for(; c < N ; ++c, l = LC)
				for(; l < M; ++l)
					if(!(ext[l][c] == 0))
						goto br;
						
			// Zeile mit gewähltem Element nach oben schieben und alle anderen Elemente durch dieses teilen
		br:
			double tmp[N << 1];
			double top = ext[l][c];
			//if(top == 0) // Dies ist erforderlich, wenn keine Lösung existiert oder das System überbestimmt ist
			//	break;
			if(l > LC)
				memcpy(tmp, ext[LC], (N << 1) * sizeof(double));
			for(int j = LC; j < (N << 1); ++j)
				ext[LC][j] = ext[l][j] / top;
			if(l > LC)
				memcpy(ext[l], tmp, (N << 1) * sizeof(double));
			
			// Erstes Element jeder Zeile durch Subtraktion von Vielfachen der ersten Zeile auf 0 bringen
			for(int i = LC + 1; i < M; ++i)
				for(int j = (N << 1) - 1; j >= c; --j)
					ext[i][j] -= ext[i][c] * ext[LC][j];
		}
		
		// Aus oberer Dreiecksmatrix Einheitsmatrix erstellen
		for(int i = M - 1; i > 0; --i)
		for(int i2 = i - 1; i2 >= 0; --i2)
		for(int j = (N << 1) - 1; j > i2; --j)
			ext[i2][j] -= ext[i2][i] * ext[i][j];
		
		// Ergebnismatrix erstellen
		mat<M, N> r;
		for(int i = 0; i < M; ++i)
			memcpy(r[i], ext[i] + N, N * sizeof(double));
		return r;
 	}
};

int main()
{
	int T;
	cin >> T;
	while(T --> 0)
	{
		mat<7, 7> m;
		for(int i = 0; i < 7; ++i)
		for(int j = 0; j < 7; ++j)
			cin >> m[i][j];
		
		mat<7, 7> unit;
		for(int i = 0; i < 7; ++i)
			unit[i][i] = 1;
		
		mat<7, 7> res = m.gaussJordan(unit); // Inverses berechnen
		for(int i = 0; i < 7; ++i)
		{
			for(int j = 0; j < 7; ++j)
				printf("%.03f \t", res[i][j]);
			cout << endl;
		}
		cout << endl;
	}
	
	mat<3, 3> m2;
	m2[0][0] = 1;
	m2[0][1] = 1;
	m2[0][2] = 1;
	m2[1][0] = 4;
	m2[1][1] = 2;
	m2[1][2] = 1;
	m2[2][0] = 9;
	m2[2][1] = 3;
	m2[2][2] = 1;
	
	vec<3> v2;
	*v2[0] = 0;
	*v2[1] = 1;
	*v2[2] = 3;
	
	vec<3> result = m2.solveLGS(v2);
	cout << *result[0] << " " << *result[1] << " " << *result[2] << endl;
}
//END
