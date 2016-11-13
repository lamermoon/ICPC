#include<iostream>
#include<vector>
#include<utility>
#include<algorithm>

using namespace std;

int BLOCK_SIZE;
int cur_answer;
vector<int> lmen;
vector<int> lwomen;
vector<int> cmen;
vector<int> cwomen;

bool cmp(const pair<pair<int, int>, int> &i, const pair<pair<int, int>, int> &j) {
    if(i.first.first / BLOCK_SIZE != j.first.first / BLOCK_SIZE) {
	return i.first.first < j.first.first;
    }
    return i.first.second < j.first.second;
}

void add(int i, int j) {
    cur_answer -= min(cmen[i], cwomen[i]);
    cur_answer -= min(cmen[j], cwomen[j]);
    if(i == j) cur_answer += min(cmen[j], cwomen[j]);
    ++cmen[i];
    ++cwomen[j];
    cur_answer += min(cmen[i], cwomen[i]);
    cur_answer += min(cmen[j], cwomen[j]);
    if(i == j) cur_answer -= min(cmen[j], cwomen[j]);
}

void remove(int i, int j) {
    cur_answer -= min(cmen[i], cwomen[i]);
    cur_answer -= min(cmen[j], cwomen[j]);
    if(i == j) cur_answer += min(cmen[j], cwomen[j]);
    --cmen[i];
    --cwomen[j];
    cur_answer += min(cmen[i], cwomen[i]);
    cur_answer += min(cmen[j], cwomen[j]);
    if(i == j) cur_answer -= min(cmen[j], cwomen[j]);
}

int main()
{
    int N, M, K;
    cin >> N >> M >> K;
    lmen.resize(N);
    lwomen.resize(N);
    cmen.resize(K);
    cwomen.resize(K);
    BLOCK_SIZE = static_cast<int>(sqrt(N));
    vector<pair<pair<int, int>, int>> queries(M);
    vector<int> answers(M);
    for(int i = 0; i < N; ++i) {
	cin >> lmen[i];
    }
    for(int i = 0; i < N; ++i) {
	cin >> lwomen[i];
    }
    for(int i = 0; i < M; ++i) {
	cin >>queries[i].first.first >> queries[i].first.second;
	queries[i].second = i;
    }
    sort(queries.begin(), queries.end(), cmp);
    int mo_left = 0, mo_right = -1;
    for(int i = 0; i < M; ++i) {
	int left = queries[i].first.first;
	int right = queries[i].first.second;
	while(mo_right < right) {
	    ++mo_right;
	    add(lmen[mo_right], lwomen[mo_right]);
	}
	while(mo_right > right) {
	    remove(lmen[mo_right], lwomen[mo_right]);
	    --mo_right;
	}
	while(mo_left < left) {
	    remove(lmen[mo_left], lwomen[mo_left]);
	    ++mo_left;
	}
	while(mo_left > left) {
	    --mo_left;
	    add(lmen[mo_left], lwomen[mo_left]);
	}
	answers[queries[i].second] = cur_answer;
    }
    for(int i = 0; i < M; ++i) {
	cout << answers[i] << endl;
    }
}
