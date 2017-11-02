package tree;

public class BTree {

    public static  Node root;
    public BTree(){
        this.root = null;
    }

    public void insert(String MAC, int time){
        if(root==null){
            root = new Node(MAC, time);
            return;
        }
        Node curr = root;
        Node parent = null;
        while(true){
            parent = curr;
            if(curr.getMAC().compareTo(MAC) > 0){
                curr = curr.left;
                if(curr==null){
                    parent.left = new Node(MAC, time);
                    return;
                }
            }else if(curr.getMAC().compareTo(MAC) < 0){
                curr = curr.right;
                if(curr==null){
                    parent.right = new Node(MAC, time);
                    return;
                }
            } else {
                curr.AddTime(time);
                return;
            }
        }
    }

    public Node find(String MAC){
        Node current = root;
        Node found = null;
        while(current!=null){
            if(current.getMAC().compareTo(MAC) == 0){
                found = current;
                current = null;
            }else if(current.getMAC().compareTo(MAC) > 0){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return found;
    }
}

