/* Fourier transform
 */

#include<iostream>
//START
#include<complex>
#include<vector>
#include<algorithm>
#include<cmath>

using namespace std;

void iterativefft(const vector<long long> &pol, vector<complex<double>> &fft, int n, bool inv)
{
    //copy pol into fft
    if(!inv) {
        for(int i = 0; i < n; ++i) {
            complex<double> cp (pol[i], 0);
            fft[i] = cp;
        }
    }
    //swap positions accordingly
    for(int i = 0, j = 0; i < n; ++i) {
        if(i < j) swap(fft[i], fft[j]);
        int m = n >> 1;
        while(1 <= m && m <= j) j -= m, m >>= 1;
        j += m;
    }
    for(int m = 1; m <= n; m <<= 1) { //<= or <
        double theta = (inv ? -1 : 1) * 2 * M_PI / m;
        complex<double> wm(cos(theta), sin(theta));
        for(int k = 0; k < n; k += m) {
            complex<double> w = 1;
            for(int j = 0; j < m/2; ++j) {
                complex<double> t = w * fft[k + j + m/2];
                complex<double> u = fft[k + j];
                fft[k + j] = u + t;
                fft[k + j + m/2] = u - t;
                w = w*wm;
            }
        }
    }
    if(inv) {
        for(int i = 0; i < n; ++i) {
            fft[i] /= complex<double> (n);
        }
    }
}

int main()
{
    int N;
    cin >> N;
    vector<long long> pol (262144);
    int min = 60000;
    int max = -60000;
    for(int i = 0; i < N; ++i) {
        int ind;
        cin >> ind;
        if(ind < min) min = ind;
        if(ind > max) max = ind;
        ++pol[ind+65536];
    }
    vector<complex<double>> fft (262144);
    iterativefft(pol, fft, 262144, false);
    for(int i = 0; i < 262144; ++i) {
        fft[i] *= fft[i];
    }
    iterativefft(pol, fft, 262144, true);
    long long sum = 0;
    for(int i = 81072; i <= 181072; ++i) {
        int ind = i - 131072;
        if(ind < min) continue;
        if(ind > max) break;
        long long resi = round(fft[i].real());
        if(ind % 2 == 0 && ind != 0) {
            resi -= pol[ind/2 + 65536] * pol[ind/2 + 65536];
            resi += pol[ind/2 + 65536]*(pol[ind/2 + 65536]-1);
        }
        resi *= pol[ind + 65536];
        if(ind != 0) {
            resi -= 2*pol[65536] * pol[ind + 65536] * pol[ind + 65536];
            resi += 2*pol[65536] * pol[ind + 65536] * (pol[ind + 65536]-1);
        }
        sum += resi;
    }
    sum -= pol[65536] * pol[65536] * pol[65536];
    sum += pol[65536] * (pol[65536] - 1) * (pol[65536] - 2);
    cout << sum << endl;
}
//END
