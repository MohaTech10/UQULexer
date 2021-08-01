package semantic.redesign;

import ast.Expression;
import ast.redesign.Identifier;
import ast.redesign.NameAliasDeclNode;
import ast.VarDecl;
import ast.type.Type;
import ast.type.TypeKind;
import ast.type.redesign.UnresolvedType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// NodeDecl creation
public class SemaDecl extends SemaBase {

    //  UnresolvedTypes - This keeps track of all of the unresolved types in the AST.
    Map<Identifier, NameAliasDeclNode> unresolvedTypes = new HashMap<>();
    List<NameAliasDeclNode> unresolvedList = new ArrayList<>();

    public SemaDecl(Sema sema) {
        super(sema);
    }
    public NameAliasDeclNode tpAliasSema(Identifier identifier, Type type, Scope ctx) {  // when see typealais decl
        var def = ctx.lookup(identifier.name);

        if (def == null) {
            var newAliasDelc = new NameAliasDeclNode(type, identifier.name);
            ctx.addEntry(0, identifier.name, newAliasDelc);
            return newAliasDelc;
        }
        if (def.getUnderlyingType().getKind() == TypeKind.UNRESOLVED_KIND) {
            def.underlyingType = type;
            unresolvedTypes.remove(identifier);
            return def;
        }
        // else means redefinition?  // You;re wrong everythin is namealias you need to fucos other wise you're dead ! question remain why you would like to do such a thing?
        System.err.println("SemaError redefinition: " + identifier + " already defined");
        return def;
        // Look it up here and then check if not exists
        // push entry to scope so then we look it up !;
    }

    public NameAliasDeclNode lookupTypename(Identifier identifier, Scope ctx) {  // when see var x: foo = 10;
        var typeDef =  ctx.lookup(identifier.name);
        if (typeDef != null)
            return typeDef;
        typeDef = new NameAliasDeclNode(new UnresolvedType(TypeKind.UNRESOLVED_KIND, "unresolved"), identifier.name);
        ctx.addEntry(0, identifier.name, typeDef);  // Todo: type should go into typeScope.insert(..);
        unresolvedTypes.put(identifier, typeDef);
        unresolvedList.add(typeDef);
        return typeDef;
    }

    public VarDecl varDeclSema(Identifier identifier, Type type, Expression init, Scope scope) {
//        Must have a type or an expression already
        var def = scope.lookup(identifier.name);
        if (def != null) {
            System.err.println("Redefinition");
        }
        else {scope.addEntry(1, identifier.name, new NameAliasDeclNode(type, identifier.name)); }
        return new VarDecl(0, identifier.name, type, init);

        // scope.lookup(identifer.name) if not null redefine !;
        // create it and add to currentScope/ the one being sent.  // sema its type, define? exist, typelaias? and similar
        // then return it;
    }
}