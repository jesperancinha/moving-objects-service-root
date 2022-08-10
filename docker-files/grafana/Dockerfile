FROM grafana/grafana:9.0.7-ubuntu

COPY datasource.yml /etc/grafana/provisioning/datasources/

COPY dashboard.yml /etc/grafana/provisioning/dashboards/

COPY jvm-grafana.json /etc/grafana/provisioning/dashboards/

COPY nodejs-grafana.json /etc/grafana/provisioning/dashboards/
