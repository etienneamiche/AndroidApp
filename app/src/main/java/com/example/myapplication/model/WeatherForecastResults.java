package com.example.myapplication.model;





import java.util.List;


public class WeatherForecastResults {

        private String cod;

        private Double message;

        private Integer cnt;

        private List<MyList> list;

        private City city;

        public String getCod() {
            return cod;
        }

        public void setCod(String cod) {
            this.cod = cod;
        }

        public Double getMessage() {
            return message;
        }

        public void setMessage(Double message) {
            this.message = message;
        }

        public Integer getCnt() {
            return cnt;
        }

        public void setCnt(Integer cnt) {
            this.cnt = cnt;
        }

        public List<MyList> getList() {
            return list;
        }

        public void setList(List<MyList> list) {
            this.list = list;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

    }

