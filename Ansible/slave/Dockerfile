FROM ubuntu

RUN apt-get update

RUN apt-get install --no-install-recommends -y software-properties-common && \
	apt-get install --no-install-recommends -y nano && \
    apt-get install --no-install-recommends -y ssh

RUN useradd -m ansible
COPY /slave-ssh-config/sshd_config /etc/ssh/sshd_config 

WORKDIR /home/ansible
RUN mkdir /home/ansible/.ssh
RUN chown ansible:ansible .ssh
RUN service ssh start

VOLUME /home/ansible/.ssh

EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]
