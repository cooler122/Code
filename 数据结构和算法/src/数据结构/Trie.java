package ���ݽṹ;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Trie{
 
    private Vertex root;			//һ��Trie����һ�����ڵ�

    protected class Vertex{			//�ڵ��ࣨ�ڲ��ࣩ
        protected int words;
        protected int prefixes;
        protected Vertex[] edges;	//ÿ���ڵ����26���ӽڵ�(����Ϊ����)
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
     * ��ӡ���е���
     * @return �����б�
     */
    public List<String> listAllWords() {
        List<String> words = new ArrayList<String>();
        Vertex[] edges = root.edges;
        for (int i = 0; i < edges.length; i++) {				//��'a'��'z'����Ȳ�ѯ
            if (edges[i] != null) {
                String word = "" + (char)('a' + i);
                depthFirstSearchWords(words, edges[i], word);
            }
        }        
        return words;
    }

     /** 
     * Depth First Search words in the Trie and add them to the List.
     * �ڴ�����ǰ׺���У�������Ȳ�ѯһ������Ƭ�Σ�������Ƭ�ε���û�нڵ㣨���Ƭ�����������ʣ�
     * ������ӵ��ʵ�һ���б���
     * @param words			��ӵ����õ��б�
     * @param vertex		��/��ǰ׺��
     * @param wordSegment	����ѯ�ĵ���Ƭ��
     */

    private void depthFirstSearchWords(List words, Vertex vertex, String wordSegment) {
        Vertex[] edges = vertex.edges;
        boolean hasChildren = false;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                hasChildren = true;
                String newWord = wordSegment + (char)('a' + i);    //�������ĵ���Ƭ��ÿ�γ��Լ����½ڵ���еݹ��ѯ            
                depthFirstSearchWords(words, edges[i], newWord);
            }            
        }
        if (!hasChildren) {
            words.add(wordSegment);
        }
    }

    /**
     * ͳ�����ַ���prefixΪǰ׺�ĵ��ʵ�����
     * @param prefix	��������ǰ׺�ַ���
     * @return			����
     */
    public int countPrefixes(String prefix) {
        return countPrefixes(root, prefix);
    }

    /**
     * ͳ��ĳ���ӽڵ��£���prefixΪǰ׺�ĵ��ʵ�����
     * @param vertex			�������Ľڵ�
     * @param prefixSegment		ǰ׺�ַ���
     * @return					����
     */
    private int countPrefixes(Vertex vertex, String prefixSegment) {
        if (prefixSegment.length() == 0) { //�ﵽһ�����ʵ����һ����ĸ
            return vertex.prefixes;
        }
        char c = prefixSegment.charAt(0);
        int index = c - 'a';
        if (vertex.edges[index] == null) { //���ʲ�����
            return 0;
        } else {
            return countPrefixes(vertex.edges[index], prefixSegment.substring(1));
        }        
    }

    /**
     * ��ѯһ�����ʵ�����
     * @param word
     * @return
     */
    public int countWords(String word) {
        return countWords(root, word);
    }    

    /**
     * �ڸ����Ľڵ��²�ѯһ�����ʵ�����
     * @param vertex		�����Ľڵ�
     * @param wordSegment	����Ƭ��
     * @return
     */
    private int countWords(Vertex vertex, String wordSegment) {
        if (wordSegment.length() == 0) { 	//�ﵽ����Ƭ�����һ���ַ�
            return vertex.words;
        }
        char c = wordSegment.charAt(0);
        int index = c - 'a';
        if (vertex.edges[index] == null) { 	//���ʲ�����
            return 0;
        } else {
            return countWords(vertex.edges[index], wordSegment.substring(1));
        }        
    }

    
    /**
     * ��ӵ���
     * @param ��Ҫ��ӵĵ���
     */
    public void addWord(String word) {
        addWord(root, word);
    }

    /** 
     * �ڸ����ڵ������ĳһ������
     * @param vertex 		�����ڵ�
     * @param word 			����ӵĵ���
     */
    private void addWord(Vertex vertex, String word) {
       if (word.length() == 0) { 					//������е��ʵ���ĸ��������ˣ���ʱ�����ĵ���Ƭ�γ���Ϊ0
            vertex.words ++;
        } else {
            vertex.prefixes ++;
            char c = word.charAt(0);
            c = Character.toLowerCase(c);
            int index = c - 'a';
            if (vertex.edges[index] == null) { 					//��������µı�Ϊ�գ����½�һ����
                vertex.edges[index] = new Vertex();
            }
            addWord(vertex.edges[index], word.substring(1)); 	//����һ����ĸ
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