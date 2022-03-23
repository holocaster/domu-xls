# domu-xls

## Objetivo 
Projeto visa percorrer todas as notas de corretagem das corretoras CLEAR, MODAL e AVENUE para montar o xls que será importado no aplicativo DOMU

## Disclaimer
* O código foi construído visando somente as ações que tenho ou que já tive em carteira
* As listas das subscrições feitas e venda de subscrições para ignorar dentro do código também são individuais
* Ignorei as taxas de corretagem em todas as notas

Como não tenho um banco de dados de todos os ativos nacionais e internacionais, terá que adicionar e remover as listas em memória
por conta própria, ou conectar a um banco de dados e puxar ou algum arquivo auxiliar para carregar.

## Uso
Passar somente a pasta onde estão todas as notas. O sistema é recursivo e irá procurar por todas os PDFs em todas as sub pastas

Somente irá verificar as linhas se encontrar o PDF de nota de corretagem. Geralmente o nome da corretora fica nas primeiras linhas.
Irá ignorar os arquivos PDFs sem o padrão do nome da corretora.
