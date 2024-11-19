
//java
//task1
//ReportSystem.java
interface IReport {
    String generate();
}

class SalesReport implements IReport {
    public String generate() {
        return "Sales Report Data";
    }
}

class UserReport implements IReport {
    public String generate() {
        return "User Report Data";
    }
}

abstract class ReportDecorator implements IReport {
    protected IReport report;

    public ReportDecorator(IReport report) {
        this.report = report;
    }

    public String generate() {
        return report.generate();
    }
}

class DateFilterDecorator extends ReportDecorator {
    private String startDate;
    private String endDate;

    public DateFilterDecorator(IReport report, String startDate, String endDate) {
        super(report);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String generate() {
        return super.generate() + " | Filtered by date range: " + startDate + " to " + endDate;
    }
}

class SortingDecorator extends ReportDecorator {
    private String criterion;

    public SortingDecorator(IReport report, String criterion) {
        super(report);
        this.criterion = criterion;
    }

    public String generate() {
        return super.generate() + " | Sorted by: " + criterion;
    }
}

class CsvExportDecorator extends ReportDecorator {
    public CsvExportDecorator(IReport report) {
        super(report);
    }

    public String generate() {
        return super.generate() + " | Exported to CSV format";
    }
}

class PdfExportDecorator extends ReportDecorator {
    public PdfExportDecorator(IReport report) {
        super(report);
    }

    public String generate() {
        return super.generate() + " | Exported to PDF format";
    }
}

class SalesAmountFilterDecorator extends ReportDecorator {
    private double minAmount;

    public SalesAmountFilterDecorator(IReport report, double minAmount) {
        super(report);
        this.minAmount = minAmount;
    }

    public String generate() {
        return super.generate() + " | Filtered by minimum sales amount: " + minAmount;
    }
}

public class ReportSystem {
    public static void main(String[] args) {
        // Создаем базовый отчет
        IReport report = new SalesReport();
        
        report = new DateFilterDecorator(report, "2023-01-01", "2023-12-31");
        report = new SortingDecorator(report, "Date");
        report = new SalesAmountFilterDecorator(report, 1000);
        report = new CsvExportDecorator(report);
        
        System.out.println(report.generate());
        
        IReport userReport = new UserReport();
        userReport = new PdfExportDecorator(new SortingDecorator(new DateFilterDecorator(userReport, "2023-06-01", "2023-06-30"), "Name"));
        
        System.out.println(userReport.generate());
    }
}


//task2
//LogisticsSystem.java
interface IInternalDeliveryService {
    void deliverOrder(String orderId);
    String getDeliveryStatus(String orderId);
    double calculateDeliveryCost(String orderId);
}
class InternalDeliveryService implements IInternalDeliveryService {
    @Override
    public void deliverOrder(String orderId) {
        System.out.println("Internal delivery initiated for order: " + orderId);
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        return "Internal delivery status for " + orderId + ": In progress";
    }

    @Override
    public double calculateDeliveryCost(String orderId) {
        return 50.0;  // Фиктивная стоимость для внутренней доставки
    }
}
class ExternalLogisticsServiceA {
    public void shipItem(int itemId) {
        System.out.println("External Logistics A: Shipping item " + itemId);
    }

    public String trackShipment(int shipmentId) {
        return "Tracking shipment with External Logistics A: ID " + shipmentId;
    }
}
class LogisticsAdapterA implements IInternalDeliveryService {
    private ExternalLogisticsServiceA externalService;
    private double costFactor = 1.2;

    public LogisticsAdapterA(ExternalLogisticsServiceA externalService) {
        this.externalService = externalService;
    }

    @Override
    public void deliverOrder(String orderId) {
        int itemId = Integer.parseInt(orderId);  // Пример преобразования ID
        externalService.shipItem(itemId);
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        int shipmentId = Integer.parseInt(orderId);
        return externalService.trackShipment(shipmentId);
    }

    @Override
    public double calculateDeliveryCost(String orderId) {
        return 100.0 * costFactor;  // Пример расчета для внешней службы A
    }
}
class ExternalLogisticsServiceB {
    public void sendPackage(String packageInfo) {
        System.out.println("External Logistics B: Sending package " + packageInfo);
    }

    public String checkPackageStatus(String trackingCode) {
        return "External Logistics B: Status for " + trackingCode;
    }
}
class LogisticsAdapterB implements IInternalDeliveryService {
    private ExternalLogisticsServiceB externalService;
    private double costFactor = 1.5;

    public LogisticsAdapterB(ExternalLogisticsServiceB externalService) {
        this.externalService = externalService;
    }

    @Override
    public void deliverOrder(String orderId) {
        externalService.sendPackage("Order ID: " + orderId);
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        return externalService.checkPackageStatus(orderId);
    }

    @Override
    public double calculateDeliveryCost(String orderId) {
        return 120.0 * costFactor;  // Пример расчета для внешней службы B
    }
}
class ExternalLogisticsServiceC {
    public void processOrder(String id) {
        System.out.println("External Logistics C: Processing order " + id);
    }

    public String orderStatus(String id) {
        return "External Logistics C: Status for order " + id;
    }
}
class LogisticsAdapterC implements IInternalDeliveryService {
    private ExternalLogisticsServiceC externalService;
    private double costFactor = 1.7;

    public LogisticsAdapterC(ExternalLogisticsServiceC externalService) {
        this.externalService = externalService;
    }

    @Override
    public void deliverOrder(String orderId) {
        externalService.processOrder(orderId);
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        return externalService.orderStatus(orderId);
    }

    @Override
    public double calculateDeliveryCost(String orderId) {
        return 150.0 * costFactor;  // Пример расчета для внешней службы C
    }
}
class DeliveryServiceFactory {
    public static IInternalDeliveryService getDeliveryService(String serviceType) {
        switch (serviceType) {
            case "internal":
                return new InternalDeliveryService();
            case "externalA":
                return new LogisticsAdapterA(new ExternalLogisticsServiceA());
            case "externalB":
                return new LogisticsAdapterB(new ExternalLogisticsServiceB());
            case "externalC":
                return new LogisticsAdapterC(new ExternalLogisticsServiceC());
            default:
                throw new IllegalArgumentException("Unknown delivery service type: " + serviceType);
        }
    }
}
public class LogisticsSystem {
    public static void main(String[] args) {
        IInternalDeliveryService internalService = DeliveryServiceFactory.getDeliveryService("internal");
        internalService.deliverOrder("101");
        System.out.println(internalService.getDeliveryStatus("101"));
        System.out.println("Delivery cost: " + internalService.calculateDeliveryCost("101"));

        IInternalDeliveryService externalServiceA = DeliveryServiceFactory.getDeliveryService("externalA");
        externalServiceA.deliverOrder("202");
        System.out.println(externalServiceA.getDeliveryStatus("202"));
        System.out.println("Delivery cost: " + externalServiceA.calculateDeliveryCost("202"));

        IInternalDeliveryService externalServiceB = DeliveryServiceFactory.getDeliveryService("externalB");
        externalServiceB.deliverOrder("303");
        System.out.println(externalServiceB.getDeliveryStatus("303"));
        System.out.println("Delivery cost: " + externalServiceB.calculateDeliveryCost("303"));
        
        IInternalDeliveryService externalServiceC = DeliveryServiceFactory.getDeliveryService("externalC");
        externalServiceC.deliverOrder("404");
        System.out.println(externalServiceC.getDeliveryStatus("404"));
        System.out.println("Delivery cost: " + externalServiceC.calculateDeliveryCost("404"));
    }
}
