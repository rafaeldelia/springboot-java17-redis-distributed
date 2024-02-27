1) adicionei o Micrometer no pom.xml
	
	Micrometer can be used to transform /actuator/metrics endpoint into the format Prometheus can understand.
	
	Tem que funcionar: http://localhost:8080/ms-cache/actuator/prometheus

2) Download no Prometheus

	2.1 - D:\_desenv\prometheus\prometheus-2.49.1.windows-amd64
	2.2 - Abrir o arquivo prometheus.yml
	2.3 - Adicionei um JOB no scrape_configs
		scrape_configs:
		  - job_name: 'ms_cache_prometheus'
		    metrics_path: '/ms-cache/actuator/prometheus'
		    scrape_interval: 5s
		    static_configs:
		      - targets: ['ms-cache:8080']
	2.4 - disclaimer - coloquei o prometheus.yml na pasta para ver como deve ser, não é para rodar daqui.
	
3) Executar o docker-compose desta pasta, que sobe a aplicação, redis e o prometheus.

	3.1 - Levar este docker-compose para a pasta que baixou o prometheus e fazer UP de lá.
	3.2 - "docker-compose -f docker-compose-redis.yml up -d"
	
4) Acessar o prometheus e olhar o gráfico. Está tudo funcionando se aparecer.

	http://localhost:9090/graph
