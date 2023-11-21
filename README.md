# Indexer

Programa desenvolvido para a disciplina DS143 - Estrutura de Dados II.

## Sinopse

O programa Indexer realiza uma contagem de palavras em documentos de texto, permitindo buscas por palavras específicas e a identificação de documentos relevantes para um termo de busca. 
Todas as letras são transformadas para minúsculas, e caracteres como números e pontuações são ignorados.

## Opções

- `--freq N ARQUIVO`: Exibe o número de ocorrências das N palavras mais frequentes em ARQUIVO, em ordem decrescente de ocorrência.
- `--freq-word PALAVRA ARQUIVO`: Exibe o número de ocorrências de PALAVRA em ARQUIVO.
- `--search TERMO ARQUIVO [ARQUIVO ...]`: Exibe uma listagem dos ARQUIVOS mais relevantes para o TERMO, em ordem descrescente de relevância. TERMO pode conter mais de uma palavra, indicada entre àspas.

## Como Usar

### Requisitos

- [Java (JDE 17)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven (3.9)](https://maven.apache.org/download.cgi)

### Compilação e Execução

Após a construção do projeto usando Maven, descompacte o arquivo `indexer-1.0-SNAPSHOT-distribution.zip`. Acesse a pasta extraída pelo terminal e utilize o comando `./indexer.sh`.

## Exemplos de Uso

### Contagem de palavras mais frequentes

```bash
./indexer.sh --freq 5 arquivo.txt
```

### Contagem de frequência de uma palavra 
```bash
./indexer.sh --freq-word palavra arquivo.txt
```

### Busca arquivos mais relevantes para um termo 
```bash
./indexer.sh --search 'termo para pesquisa' arquivo.txt [arquivo2.txt] [arquivo3.txt]
```

## TF-IDF (Term Frequency-Inverse Document Frequency)

O programa utiliza o cálculo TF-IDF para determinar a relevância de documentos em uma busca por termo.
