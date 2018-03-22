class CustomMap<K, V>{


  List<Integer> keys = new ArrayList<Integer>();
  List<V> values = new ArrayList<V>();
  int size = 0;
  public void put(K key, V value){
    int index = keys.indexOf(key.hashCode());
    if(size == 0 || index == -1){
      keys.add(key.hashCode());
      values.add(value);
      size++;
    }
    else{
      keys.set(index, key.hashCode());
      values.set(index, value);
    }
  }
  
  public V get(K key){
    int index = keys.indexOf(key.hashCode());
    if(index == -1){return null;}
    return values.get(index);
  }
  
  public int getSize(){
    return size;
  }
  
  public void remove(K key){
    int index = keys.indexOf(key.hashCode());
    if(index == -1){return;}
    keys.remove(index);
    values.remove(index);
    size--;
  }
}