import React from "react";
import SockJsClient from "react-stomp";
import Fetch from "json-fetch";
import { TalkBox } from "react-talk";

class Chat extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      clientConnected: false,
      messages: []
    };
  }

  onMessageReceive = (msg, topic) => {
    this.setState(prevState => ({
      messages: [...prevState.messages, msg]
    }));
  };

  sendMessage = (msg, selfMsg) => {
    try {
      //What is clientRef??
      this.clientRef.sendMessage("/app/all", JSON.stringify(selfMsg));
      return true;
    } catch (e) {
      return false;
    }
  };

  componentWillMount() {
    Fetch("/history", {
      method: "GET"
    }).then((response) => {
      this.setState({ messages: response.body });
    });
  }

  render() {
    const wsSourceUrl = window.location.protocol + "//" + window.location.host + "/chat";
    // const wsSourceUrl = 'http://localhost:8080/chat';
    return (
      <div>
        <TalkBox topic="react-websocket-template" currentUserId={ this.randomUserId }
          currentUser={ this.randomUserName } messages={ this.state.messages }
          onSendMessage={ this.sendMessage } connected={ this.state.clientConnected }/>

        <SockJsClient url={ wsSourceUrl } topics={["/topic/chat"]}
          onMessage={ this.onMessageReceive } ref={ (client) => { this.clientRef = client }}
          onConnect={ () => { this.setState({ clientConnected: true }) } }
          onDisconnect={ () => { this.setState({ clientConnected: false }) } }
          debug={ false }/>
      </div>
    );
  }
}

export default Chat;
