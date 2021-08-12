package ast.decl_def;

import ast.Identifier;
import ast.type.Type;
import ast.type.NameAliasType;
import compile.utils.ShapeDump;
import lexer.Position;

public class TypeAliasDecl extends Decl {
    public Position location;  // typealias word loc.
    public Type underlyingType;

    NameAliasType nameAliasType;
    public Identifier identifier;

    public TypeAliasDecl(Type underlyingType, Identifier identifier) {
        super(DeclKind.AlisaDecl);
        this.underlyingType = underlyingType;
        this.identifier = identifier;
    }

    public NameAliasType getNameAliasType() {
        return (nameAliasType == null) ? new NameAliasType(this) : nameAliasType;
    }
    protected void dump(int indent) {
        for (int i = 0; i < indent; i++) {
            ShapeDump.spaceTreeShape(ShapeDump.BasicShape.WhiteSpace);
        }
        System.out.println("TypeAliasDecl of " + underlyingType);
    }

}
