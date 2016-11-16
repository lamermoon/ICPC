/* comparator in C++
 */
#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

using namespace std;
//START
bool myfunction (int i, int j) {return (i<j); }

int main() {
    vector<int> vec;
    sort(vec.begin(), vec.end(), myfunction);
    priority_queue<int, vector<int>, decltype(myfunction) *> pq(myfunction);
}
//END
