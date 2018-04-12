import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Condition;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        FileExplorer fe = new FileExplorer();
        // can we make it so we don't specify this??
        File root = new File("C:\\Users\\Terran\\IdeaProjects\\Bepis\\src\\Main.java");
        fe.traverseFolder(root);
        for (File f : fe.getFiles()) {
            System.out.println(f.getName());
            LayoutObfuscator lo = new LayoutObfuscator();
            System.out.println(lo.IntoALine(f));
//            CompilationUnit unit = JavaParser.parse(f);
//
//            for(ClassOrInterfaceDeclaration cls: unit.findAll(ClassOrInterfaceDeclaration.class)) {
//                ClassFlattener cf = new ClassFlattener(cls);
//                cf.createStatements();
//                cf.createSwitch();
//            }
//
//
//
//            System.out.println(unit);
        }

    }


}
