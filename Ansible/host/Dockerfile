FROM ubuntu

RUN apt-get update 

RUN apt-get install --no-install-recommends -y software-properties-common && \
    apt-add-repository ppa:ansible/ansible && \
    apt-get update && \
    apt-get install -y ansible

RUN echo '[local]\nlocalhost\n' > /etc/ansible/hosts

RUN useradd -m ansible

WORKDIR /home/ansible
RUN mkdir /home/ansible/.ssh
RUN chown ansible:ansible .ssh

VOLUME /home/ansible/.ssh

USER ansible
