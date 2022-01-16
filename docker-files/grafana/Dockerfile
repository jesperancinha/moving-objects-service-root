FROM grafana/grafana:6.5.3-ubuntu

COPY datasource.yml /etc/grafana/provisioning/datasources/

COPY dashboard.yml /etc/grafana/provisioning/dashboards/

COPY jvm-grafana.json /etc/grafana/provisioning/dashboards/

COPY nodejs-grafana.json /etc/grafana/provisioning/dashboards/
