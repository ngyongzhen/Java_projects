 private int[] merge(int[] a, int[] b){
        int[] res = new int[a.length + b.length];
        int arr1Index = 0;
        int arr2Index = 0;
        int resIndex = 0;
        while(resIndex < res.length){
            if(arr1Index == a.length){
                // Just do b
                res[resIndex] = b[arr2Index];
                arr2Index++;
            }else if(arr2Index == b.length){
                // Just do a
               res[resIndex] = a[arr1Index];
                arr1Index++;
            } else{
                if(a[arr1Index] < b[arr2Index]){
                    res[resIndex] = a[arr1Index];
                    arr1Index++;
                }else{
                    res[resIndex] = b[arr2Index];
                    arr2Index++;
                }
            }

            resIndex++;
        }
        return res;
    }