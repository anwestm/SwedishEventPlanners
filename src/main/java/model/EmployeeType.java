package model;

public enum EmployeeType {

    HR_EMPLOYEE("HR-Employee"),
    CUSTOMER_SERVICE("Customer Service"),
    SENIOR_CUSTOMER_SERVICE("Senior Customer Service"),
    FINANCIAL_MANAGER("Financial Manager"),
    ADMINISTRATION_MANAGER("Administration Manager"),
    PRODUCTION_MANAGER("Production Manager"),
    SERVICE_MANAGER("Service Manager"),
    CHEF("Chef"),
    PHOTOGRAPHER("Photographer"),
    DEBUG("DEBUG");

    private String name;

    EmployeeType(String str) {
        this.name = str;
    }

    @Override
    public String toString() {
        return name;
    }
}
