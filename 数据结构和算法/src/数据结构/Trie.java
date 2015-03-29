package 数据结构;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Trie{
 
    private Vertex root;			//一个Trie树有一个根节点

    protected class Vertex{			//节点类（内部类）
        protected int words;
        protected int prefixes;
        protected Vertex[] edges;	//每个节点包含26个子节点(类型为自身)
        Vertex() {
            words = 0;
            prefixes = 0;
            edges = new Vertex[26];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = null;
            }
        }
    }

  
    public Trie () {
        root = new Vertex();
    }

   
    /** 
     * 打印所有单词
     * @return 单词列表
     */
    public List<String> listAllWords() {
        List<String> words = new ArrayList<String>();
        Vertex[] edges = root.edges;
        for (int i = 0; i < edges.length; i++) {				//从'a'到'z'都深度查询
            if (edges[i] != null) {
                String word = "" + (char)('a' + i);
                depthFirstSearchWords(words, edges[i], word);
            }
        }        
        return words;
    }

     /** 
     * Depth First Search words in the Trie and add them to the List.
     * 在传来的前缀树中，深度优先查询一个单词片段，如果这个片段地下没有节点（则此片段是完整单词）
     * 并且添加单词到一个列表中
     * @param words			添加单词用的列表
     * @param vertex		根/子前缀树
     * @param wordSegment	待查询的单词片段
     */

    private void depthFirstSearchWords(List words, Vertex vertex, String wordSegment) {
        Vertex[] edges = vertex.edges;
        boolean hasChildren = false;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                hasChildren = true;
                String newWord = wordSegment + (char)('a' + i);    //传进来的单词片段每次尝试加上新节点进行递归查询            
                depthFirstSearchWords(words, edges[i], newWord);
            }            
        }
        if (!hasChildren) {
            words.add(wordSegment);
        }
    }

    /**
     * 统计以字符串prefix为前缀的单词的数量
     * @param prefix	传进来的前缀字符串
     * @return			数量
     */
    public int countPrefixes(String prefix) {
        return countPrefixes(root, prefix);
    }

    /**
     * 统计某个子节点下，以prefix为前缀的单词的数量
     * @param vertex			穿件来的节点
     * @param prefixSegment		前缀字符串
     * @return					数量
     */
    private int countPrefixes(Vertex vertex, String prefixSegment) {
        if (prefixSegment.length() == 0) { //达到一个单词的最后一个字母
            return vertex.prefixes;
        }
        char c = prefixSegment.charAt(0);
        int index = c - 'a';
        if (vertex.edges[index] == null) { //单词不存在
            return 0;
        } else {
            return countPrefixes(vertex.edges[index], prefixSegment.substring(1));
        }        
    }

    /**
     * 查询一个单词的数量
     * @param word
     * @return
     */
    public int countWords(String word) {
        return countWords(root, word);
    }    

    /**
     * 在给定的节点下查询一个单词的数量
     * @param vertex		给定的节点
     * @param wordSegment	单词片段
     * @return
     */
    private int countWords(Vertex vertex, String wordSegment) {
        if (wordSegment.length() == 0) { 	//达到单词片段最后一个字符
            return vertex.words;
        }
        char c = wordSegment.charAt(0);
        int index = c - 'a';
        if (vertex.edges[index] == null) { 	//单词不存在
            return 0;
        } else {
            return countWords(vertex.edges[index], wordSegment.substring(1));
        }        
    }

    
    /**
     * 添加单词
     * @param 需要添加的单词
     */
    public void addWord(String word) {
        addWord(root, word);
    }

    /** 
     * 在给定节点下添加某一个单词
     * @param vertex 		给定节点
     * @param word 			被添加的单词
     */
    private void addWord(Vertex vertex, String word) {
       if (word.length() == 0) { 					//如果所有单词的字母都被添加了，此时传来的单词片段长度为0
            vertex.words ++;
        } else {
            vertex.prefixes ++;
            char c = word.charAt(0);
            c = Character.toLowerCase(c);
            int index = c - 'a';
            if (vertex.edges[index] == null) { 					//如果几点下的边为空，就新建一个边
                vertex.edges[index] = new Vertex();
            }
            addWord(vertex.edges[index], word.substring(1)); 	//到下一个字母
        }
    }

    public static void main(String args[]){
     Trie trie = new Trie();
     trie.addWord("China");
     trie.addWord("China");
     trie.addWord("Chinal");

     trie.addWord("crawl");
     trie.addWord("crime");
     trie.addWord("ban");
     trie.addWord("China");

     trie.addWord("english");
     trie.addWord("establish");
     trie.addWord("eat");
     System.out.println(trie.root.prefixes);
     System.out.println(trie.root.words);
     List< String> list = trie.listAllWords();
     Iterator listiterator = list.listIterator();
     
     while(listiterator.hasNext()){
          String s = (String)listiterator.next();
          System.out.println(s);
     }
     int count = trie.countPrefixes("c");
     int count1=trie.countWords("china");
     System.out.println("the count of c prefixes:"+count);
     System.out.println("the count of china countWords:"+count1);
    }
}