package ast;

import ast.type.BuiltinType;
import ast.type.Type;
import ast.type.TypeAliasKind;
import ast.type.TypeKind;
import compile.utils.ShapeDump;
import semantic.Context;
import semantic.Definition;

public class TypeAliasDecl extends Statement {
    private Type statementType;
    String thaAliasName;
    Type underlayType;

    public TypeAliasDecl(int line, String thaAliasName, Type underlayType) {
        super(line);
        this.thaAliasName = thaAliasName;
        this.underlayType = underlayType;
    }



    @Override
    public ASTNode semaAnalysis(Context context) {
        statementType = new TypeAliasKind(TypeKind.TYPEALIAS_KIND, this.thaAliasName);
        ((TypeAliasKind)statementType).underlay = underlayType;
        if (!(underlayType instanceof BuiltinType))   // for now check here
            System.err.println("Alias for unknown type");
        else
            context.addEntry(getLine(), thaAliasName, new Definition(statementType));   // Again: hard-coded types no need to look them up. Fix if user-defined allowed

        return this;
    }

    @Override
    protected void dump(int indent) {
        for (int i = 0; i < indent; i++) {
            ShapeDump.spaceTreeShape(ShapeDump.BasicShape.WhiteSpace);
        }
        System.out.println("TypeAliasDecl of " + underlayType);
    }
}
