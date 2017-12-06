FROM ubuntu

RUN apt-get update 

RUN apt-get install --no-install-recommends -y software-properties-common && \
    apt-add-repository ppa:ansible/ansible && \
    apt-get update && \
    apt-get install -y ansible

RUN echo '[local]\nlocalhost\n' > /etc/ansible/hosts
RUN echo '[remote]\nremote-server\n' >> /etc/ansible/hosts

RUN mkdir /playbooks

VOLUME /playbooks
