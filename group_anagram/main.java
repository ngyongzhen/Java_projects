class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> retList = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        if(strs.length > 0){
            for(String s : strs){
                char[] chars = s.toCharArray();
                Arrays.sort(chars);
                String key = new String(chars);
                if(map.isEmpty() || map.get(key) == null){
                    map.put(key, new ArrayList<String>());
                }
                map.get(key).add(s);
            }
        }
        
        return new ArrayList(map.values());
    }
}