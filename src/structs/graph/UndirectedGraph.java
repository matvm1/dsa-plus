package structs.graph;

public class UndirectedGraph extends Graph{
    public UndirectedGraph(String pathToCSV)
    {
        super(pathToCSV);
    }

    public Graph generateMinSpanTree(char mode) {
        if(mode == 'k')
            return generateMinSpanTreeKruskal();
        //if(mode == 'p')
        //    return generateMinSpanTreePrim();
        return null;
    }

    private Graph generateMinSpanTreeKruskal(){
        return null;
    }

}