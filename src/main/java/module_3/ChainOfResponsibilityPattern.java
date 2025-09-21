
package module_3;

class HttpRequest {
    private final String path;
    private final String token;
    private final String role;

    public HttpRequest(String path, String token, String role) {
        this.path = path;
        this.token = token;
        this.role = role;
    }

    public String getPath() {
        return path;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}

abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return this.next;
    }

    public void handle(HttpRequest request) {
        if (canHandle(request) && next != null) {
            next.handle(request);
        }
    }

    protected abstract boolean canHandle(HttpRequest request);
}

class Logger extends Middleware {
    protected boolean canHandle(HttpRequest request) {
        if (request == null || request.getPath() == null) {
            System.out.println("Logger: Can't process an empty request!");
            return false;
        } else {
            System.out.println("Logger: Request to " + request.getPath());
            return true;
        }
    }
}

class Authentication extends Middleware {
    protected boolean canHandle(HttpRequest request) {
        if (request.getToken() == null || request.getToken().isEmpty()) {
            System.out.println("Authentication: Can't process an unauthorized request!");
            return false;
        } else {
            System.out.println("Authentication: Token accepted");
            return true;
        }
    }
}

class Role extends Middleware {
    private final String requiredRole;

    public Role(String requiredRole) {
        this.requiredRole = requiredRole;
    }

    public String getRequiredRole() {
        return requiredRole;
    }

    protected boolean canHandle(HttpRequest request) {
        if (request.getRole().equals(getRequiredRole())) {
            System.out.println("Role: Access granted");
            return true;
        } else {
            System.out.println("Role: Access denied. Required role: " + getRequiredRole());
            return false;
        }
    }
}

class BusinessLogic extends Middleware {
    protected boolean canHandle(HttpRequest request) {
        System.out.println("BusinessLogic: Process request: " + request.getPath());
        return true;
    }
}

public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Middleware chain = new Logger();
        chain.linkWith(new Authentication()).linkWith(new Role("admin")).linkWith(new BusinessLogic());

        System.out.println("Sending request...");
        chain.handle(new HttpRequest("api/users", null, "guest"));
        System.out.println("\nSending request...");
        chain.handle(new HttpRequest("api/users", "token", "user"));
        System.out.println("\nSending request...");
        chain.handle(new HttpRequest("api/users", "token", "admin"));
    }
}