package com.qixiu.wigit.viepager_active;

public class ProductBean {
        private String name;
        private String url;
        private String tiltle;
        private String price;

    public ProductBean(String name, String url, String tiltle, String price) {
        this.name = name;
        this.url = url;
        this.tiltle = tiltle;
        this.price = price;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTiltle() {
            return tiltle;
        }

        public void setTiltle(String tiltle) {
            this.tiltle = tiltle;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }