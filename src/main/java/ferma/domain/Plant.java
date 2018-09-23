package ferma.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Plant {

    @JsonProperty("name")
    private String name;
    @JsonProperty("priceBy")
    private Long priceBy;
    @JsonProperty("priceShell")
    private Long priceShell;
    @JsonProperty("time")
    private Long time;

    public Plant() {
    }

    public Plant(String name, Long priceBy, Long priceShell, Long time) {
        this.name = name;
        this.priceBy = priceBy;
        this.priceShell = priceShell;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Long getPriceBy() {
        return priceBy;
    }

    public Long getPriceShell() {
        return priceShell;
    }

    public Long getTime() {
        return time;
    }


    @Override
    public String toString() {
        return "Растение "+name
                +" цена посадки "+priceBy
                +" цена урожая "+ priceShell
                +" время созреваниея "+time+" минут";
    }
}
