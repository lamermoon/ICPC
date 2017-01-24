/* hashing pair in C++
 */
//START
struct pairhash {
public:
  template <typename T, typename U>
  std::size_t operator()(const std::pair<T, U> &x) const
  {
    return std::hash<T>()(x.first) ^ std::hash<U>()(x.second);
  }
}; 

int main() {
    unordered_map<pair<unsigned int, char>, double, pairhash> T; 
}
//END
