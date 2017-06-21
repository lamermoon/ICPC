/* Ternary Search
*/
long long msqe(long long i, long long j, long long p) {
  long long sum = 0;
  for(int id = i; id <= j; id++) {
    sum += vals[id][1] * (p - vals[id][0]) * (p - vals[id][0]);
  }
  return sum;
}

\\START
int main() {
  int d, k;
  cin >> d >> k;
  for(int i = 0; i < d; ++i) {
    cin >> vals[i][0] >> vals[i][1];
  }
  for(long long i = 0; i < d; ++i) {
    for(long long j = i; j < d; ++j) {
      long long left = vals[i][0], right = vals[j][0];
      while(left < right) {
	long long lt = left + (right - left)/3;
	long long rt = right - (right -left)/3;
	\\msqe can be any quadratic function
	if(msqe(i, j, lt) > msqe(i, j, rt)) left = lt + 1;
	else right = rt - 1;
      }
      f[i][j] = msqe(i, j, left);
    }
  }
  for(int i = 1; i <= d; ++i) {
    T[i][0] = f[0][i-1]; 
  }
  for(int i = 0; i <= d; ++i) {
    for(int j = 1; j < k; ++j) {
      T[i][j] = (1LL << 60);
      for(int l = 0; l < i; ++l) {
	T[i][j] = min(T[i][j], T[l][j-1] + f[l][i-1]);
      }
    }
  }
  cout << T[d][k-1] << endl;
}

\\END
